package com.javaacademy.cryptowallet.service;

import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.mapper.CryptoAccountMapper;
import com.javaacademy.cryptowallet.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoService {
    private final CryptoRepository cryptoRepository;
    private final CryptoAccountMapper accountMapper;

    public CryptoAccountDto getAccountByUuid(UUID uuid) {
        return accountMapper.convertToDto(cryptoRepository.getAccountByUuid(uuid).orElseThrow());
    }

    public List<CryptoAccountDto> getAllAccountsByLogin(String login) {
        return cryptoRepository.getAllAccountsByLogin(login).stream()
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

        cryptoRepository.saveAccount(account);
        log.info("Криптоаккаунт успешно создан!");
    }

}
