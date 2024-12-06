package com.javaacademy.cryptowallet.storageDb;

import com.javaacademy.cryptowallet.entity.CryptoAccount;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CryptoStorage {
    private Map<UUID, CryptoAccount> cryptoStorageMap = new HashMap<>();

    public void save(CryptoAccount cryptoAccount) {
        UUID uuid = cryptoAccount.getUuid();
        if (cryptoStorageMap.containsKey(uuid)) {
            throw new RuntimeException("Пользователь с указанным UUID уже существует!");
        }

        cryptoStorageMap.put(uuid, cryptoAccount);
    }

    public List<CryptoAccount> getAll() {
        return cryptoStorageMap.values().stream().toList();
    }

    public Optional<CryptoAccount> getAccountByUuid(UUID uuid) {
        return Optional.ofNullable(cryptoStorageMap.get(uuid));
    }

    public Optional<List<CryptoAccount>> getAllAccountsByLogin(String login) {
        return Optional.of(cryptoStorageMap.values().stream()
                .filter(cryptoAccount -> Objects.equals(cryptoAccount.getUserLogin(), login))
                .toList());
    }
}
