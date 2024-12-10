package com.javaacademy.cryptowallet.storageDb;

import com.javaacademy.cryptowallet.entity.CryptoAccount;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
public class CryptoStorage {
    private final Map<UUID, CryptoAccount> cryptoStorageMap = new HashMap<>();

//    public void saveAccount(CryptoAccount cryptoAccount) {
//        UUID uuid = cryptoAccount.getUuid();
//        if (cryptoStorageMap.containsKey(uuid)) {
//            throw new RuntimeException("Пользователь с указанным UUID уже существует!");
//        }
//
//        cryptoStorageMap.put(uuid, cryptoAccount);
//    }

//    public List<CryptoAccount> getAll() {
//        return cryptoStorageMap.values().stream().toList();
//    }
//
//    public Optional<CryptoAccount> getAccountByUuid(UUID uuid) {
//        return Optional.ofNullable(cryptoStorageMap.get(uuid));
//    }

//    public List<CryptoAccount> getAllAccountsByLogin(String login) {
//        return cryptoStorageMap.values().stream()
//                .filter(cryptoAccount -> Objects.equals(cryptoAccount.getUserLogin(), login))
//                .toList();
//    }
}
