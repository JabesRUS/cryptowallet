package com.javaacademy.cryptowallet.repository;

import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.mapper.CryptoAccountMapper;
import com.javaacademy.cryptowallet.storage.CryptoAccountStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CryptoAccountRepository {
    private final CryptoAccountStorage cryptoAccountStorage;
    private final CryptoAccountMapper cryptoAccountMapper;

    public void saveAccount(CryptoAccount account) {
        UUID uuid = account.getUuid();
        if (cryptoAccountStorage.getCryptoStorageMap().containsKey(uuid)) {
            throw new RuntimeException("Пользователь с указанным UUID уже существует!");
        }

        cryptoAccountStorage.getCryptoStorageMap().put(uuid, account);
    }

    public List<CryptoAccountDto> getAllAccounts() {
        return cryptoAccountStorage.getCryptoStorageMap().values().stream()
                .map(account -> cryptoAccountMapper.convertToDto(account))
                .toList();
    }

    public Optional<CryptoAccount> getAccountByUuid(UUID uuid) {
        return Optional.ofNullable(cryptoAccountStorage.getCryptoStorageMap().get(uuid));

    }

    public List<CryptoAccount> getAllAccountsByLogin(String login) {
        return cryptoAccountStorage.getCryptoStorageMap().values().stream()
                .filter(cryptoAccount -> Objects.equals(cryptoAccount.getUserLogin(), login))
                .toList();
    }
}
