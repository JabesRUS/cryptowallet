package com.javaacademy.cryptowallet.service;

import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.mapper.CryptoAccountMapper;
import com.javaacademy.cryptowallet.repository.CryptoAccountRepository;
import com.javaacademy.cryptowallet.service.crypto_to_usd.CryptoToUsdService;
import com.javaacademy.cryptowallet.service.exchange.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoAccountService {
    public static final int ROUNDING = 10;
    public static final int ZERO = 0;
    public static final String INSUFFICIENT_FUNDS_MESSAGE = "Недостаточно средств на счету.";
    public static final String TEMPLATE_SUCCESSFUL_OPERATION = "Операция прошла успешно. Продано %s %s.";
    private final CryptoAccountRepository cryptoAccountRepository;
    private final CryptoAccountMapper accountMapper;
    private final CryptoToUsdService cryptoToUsdService;
    private final ExchangeService exchangeService;

    public CryptoAccountDto getAccountByUuid(UUID uuid) {
        return accountMapper.convertToDto(cryptoAccountRepository.getAccountByUuid(uuid).orElseThrow());
    }

    public List<CryptoAccountDto> getAllAccountsByLogin(String login) {
        return cryptoAccountRepository.getAllAccountsByLogin(login).stream()
                .map(account -> accountMapper.convertToDto(account))
                .toList();
    }

    public void saveAccount(String login, CryptoCurrency currency) {
        CryptoAccount account = createAccount(login, currency);

        cryptoAccountRepository.saveAccount(account);
        log.info("Криптоаккаунт успешно создан!- %s".formatted(account.getUuid()));
    }

    public void refillAccount(UUID uuid, BigDecimal amountRub) {
        changeBalanceAdd(uuid, amountRub);
    }

    public void withdrawFromAccount(UUID uuid, BigDecimal amountRub) {
        changeBalanceSubtract(uuid, amountRub);
    }

    public BigDecimal getRubAmountAllAccountsByLogin(String login) {
        return cryptoAccountRepository.getAllAccountsByLogin(login).stream()
                .map(account -> account.getUuid())
                .map(uuid -> getRubAmountByUuid(uuid))
                .reduce(BigDecimal.ZERO, (accumulator, current) -> accumulator.add(current));
    }

    public BigDecimal getRubAmountByUuid(UUID uuid) {
        CryptoAccount cryptoAccount = cryptoAccountRepository.getAccountByUuid(uuid).orElseThrow();
        CryptoCurrency currency = cryptoAccount.getCurrency();
        BigDecimal currentRateUsd = cryptoToUsdService.getRateUsd(currency);
        BigDecimal balanceOnAccount = cryptoAccount.getAmountOnAccount();
        return balanceOnAccount.multiply(currentRateUsd);

    }

    private CryptoAccount createAccount(String login, CryptoCurrency currency) {
        UUID uuid = UUID.randomUUID();

        return CryptoAccount.builder()
                .userLogin(login)
                .currency(currency)
                .amountOnAccount(BigDecimal.ZERO)
                .uuid(uuid)
                .build();
    }

    private void changeBalanceAdd(UUID uuid, BigDecimal amountRub) {
        BigDecimal amountRubInCrypto = getAmountInCrypto(uuid, amountRub);
        CryptoAccount cryptoAccount = cryptoAccountRepository.getAccountByUuid(uuid).orElseThrow();

        BigDecimal balanceOnAccount = cryptoAccount.getAmountOnAccount();
        BigDecimal newBalance = balanceOnAccount.add(amountRubInCrypto);

        cryptoAccount.setAmountOnAccount(newBalance);
    }

    private void changeBalanceSubtract(UUID uuid, BigDecimal amountRub) {
        BigDecimal amountRubInCrypto = getAmountInCrypto(uuid, amountRub);
        CryptoAccount cryptoAccount = cryptoAccountRepository.getAccountByUuid(uuid).orElseThrow();
        BigDecimal balanceOnAccount = cryptoAccount.getAmountOnAccount();
        BigDecimal newBalance = balanceOnAccount.subtract(amountRubInCrypto);

        if (newBalance.compareTo(BigDecimal.ZERO) < ZERO) {
            throw new RuntimeException(INSUFFICIENT_FUNDS_MESSAGE);
        }

        log.info(TEMPLATE_SUCCESSFUL_OPERATION.formatted(amountRubInCrypto, cryptoAccount.getCurrency()));
        cryptoAccount.setAmountOnAccount(newBalance);
    }

    private BigDecimal getAmountInCrypto(UUID id, BigDecimal amountRub) {
        CryptoAccount cryptoAccount = cryptoAccountRepository.getAccountByUuid(id).orElseThrow();
        CryptoCurrency currency = cryptoAccount.getCurrency();
        BigDecimal currentRateUsd = cryptoToUsdService.getRateUsd(currency);
        BigDecimal amountInUsd = exchangeService.convertRubToUsd(amountRub);
        log.info(String.valueOf(amountInUsd.divide(currentRateUsd, ROUNDING, RoundingMode.HALF_DOWN)));
        return amountInUsd.divide(currentRateUsd, ROUNDING, RoundingMode.HALF_DOWN);
    }

}
