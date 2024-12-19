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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoAccountService {
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

    public void createAccount(String login, CryptoCurrency currency) {
        CryptoAccount account = new CryptoAccount();
        UUID uuid = UUID.randomUUID();

        account.setUserLogin(login);
        account.setCurrency(currency);
        account.setAmountOnAccount(BigDecimal.ZERO);
        account.setUuid(uuid);

        cryptoAccountRepository.saveAccount(account);
        log.info("Криптоаккаунт успешно создан!- %s".formatted(uuid));
    }

    public void refillAccount(UUID uuid, BigDecimal amountRub) {
        changeBalance(uuid, amountRub, TypeOperation.ADD);
    }

    public void withdrawFromAccount(UUID uuid, BigDecimal amountRub) {
        changeBalance(uuid, amountRub, TypeOperation.SUBTRACT);
    }

    private void changeBalance(UUID uuid, BigDecimal amountRub, TypeOperation type) {
        BigDecimal amountInCrypto = getAmountInCrypto(uuid, amountRub);
        CryptoAccount cryptoAccount = cryptoAccountRepository.getAccountByUuid(uuid).orElseThrow();

        BigDecimal balanceOnAccount = cryptoAccount.getAmountOnAccount();
        BigDecimal newBalance = switch (type) {
            case ADD -> balanceOnAccount.add(amountInCrypto);
            case SUBTRACT -> balanceOnAccount.subtract(amountInCrypto);
        };

        if (type.equals(TypeOperation.SUBTRACT) && newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Недостаточно средств на счету.");
        } else if (type.equals(TypeOperation.SUBTRACT)) {
            log.info("Операция прошла успешно. Продано %s %s.".formatted(amountInCrypto, cryptoAccount.getCurrency()));
        }

        cryptoAccount.setAmountOnAccount(newBalance);
    }

    public BigDecimal getRubAmountAllAccountByLogin(String login) {
        return cryptoAccountRepository.getAllAccountsByLogin(login).stream()
                .map(account -> account.getUuid())
                .map(uuid -> getRubAmountByUuid(uuid))
                .reduce(BigDecimal.ZERO, (accumulator, curent) -> accumulator.add(curent));
    }

    public BigDecimal getRubAmountByUuid(UUID uuid) {
        CryptoAccount cryptoAccount = cryptoAccountRepository.getAccountByUuid(uuid).orElseThrow();
        CryptoCurrency currency = cryptoAccount.getCurrency();
        BigDecimal currentRateUsd = cryptoToUsdService.getRateUsd(currency);
        BigDecimal balanceOnAccount = cryptoAccount.getAmountOnAccount();
        return balanceOnAccount.multiply(currentRateUsd);

    }

    private BigDecimal getAmountInCrypto(UUID id, BigDecimal amountRub) {
        CryptoAccount cryptoAccount = cryptoAccountRepository.getAccountByUuid(id).orElseThrow();
        CryptoCurrency currency = cryptoAccount.getCurrency();
        BigDecimal currentRateUsd = cryptoToUsdService.getRateUsd(currency);
        BigDecimal amountInUsd = exchangeService.convertRubToUsd(amountRub);
        return amountInUsd.divide(currentRateUsd, 2, RoundingMode.HALF_DOWN);
    }

}
