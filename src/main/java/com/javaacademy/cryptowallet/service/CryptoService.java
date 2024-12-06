package com.javaacademy.cryptowallet.service;

import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.entity.User;
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
    private final UserService userService;

    public CryptoAccount getAccountByUuid(UUID uuid) {
        return cryptoRepository.getAccountByUuid(uuid).orElseThrow();
    }

    public List<CryptoAccount> getAllAccountsByLogin(String login) {
        userService.getUserByLogin(login);
        return cryptoRepository.getAllAccountsByLogin(login);
    }

    public UUID createAccount(String login, CryptoCurrency currency) {
        userService.getUserByLogin(login);
        CryptoAccount account = new CryptoAccount();
        UUID uuid = UUID.randomUUID();

        account.setUserLogin(login);
        account.setCurrency(currency);
        account.setAmountOnAccount(BigDecimal.ZERO);
        account.setUuid(uuid);

        cryptoRepository.saveAccount(account);
        log.info("Криптоаккаунт успешно создан!");
        return uuid;
    }

}
