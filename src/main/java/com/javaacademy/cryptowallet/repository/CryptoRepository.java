package com.javaacademy.cryptowallet.repository;

import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.mapper.CryptoAccountMapper;
import com.javaacademy.cryptowallet.storageDb.CryptoStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CryptoRepository {
    private final CryptoStorage cryptoStorage;
    private final CryptoAccountMapper cryptoAccountMapper;

    public void saveAccount(CryptoAccount account) {
        UUID uuid = UUID.randomUUID();
        if (cryptoStorage.getCryptoStorageMap().containsKey(uuid)) {
            throw new RuntimeException("Пользователь с указанным UUID уже существует!");
        }

        cryptoStorage.getCryptoStorageMap().put(uuid, account);
    }

    public List<CryptoAccountDto> getAllAccounts() {
        return cryptoStorage.getCryptoStorageMap().values().stream()
                .map(account -> cryptoAccountMapper.convertToDto(account))
                .toList();
    }

    public Optional<CryptoAccount> getAccountByUuid(UUID uuid) {
        return Optional.ofNullable(cryptoStorage.getCryptoStorageMap().get(uuid));

    }

    public List<CryptoAccount> getAllAccountsByLogin(String login) {
        return cryptoStorage.getCryptoStorageMap().values().stream()
                .filter(cryptoAccount -> Objects.equals(cryptoAccount.getUserLogin(), login))
                .toList();
    }
}
