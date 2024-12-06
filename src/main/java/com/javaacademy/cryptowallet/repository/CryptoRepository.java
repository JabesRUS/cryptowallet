package com.javaacademy.cryptowallet.repository;

import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.storageDb.CryptoStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CryptoRepository {
    private final CryptoStorage cryptoStorage;

    public void saveAccount(CryptoAccount account) {
        cryptoStorage.saveAccount(account);
    }

    public List<CryptoAccount> getAllAccounts() {
        return cryptoStorage.getAll();
    }

    public Optional<CryptoAccount> getAccountByUuid(UUID uuid) {
        return cryptoStorage.getAccountByUuid(uuid);
    }

    public List<CryptoAccount> getAllAccountsByLogin(String login) {
        return cryptoStorage.getAllAccountsByLogin(login);
    }
}
