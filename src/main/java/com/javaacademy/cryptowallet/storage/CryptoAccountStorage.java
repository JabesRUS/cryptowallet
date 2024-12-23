package com.javaacademy.cryptowallet.storage;

import com.javaacademy.cryptowallet.entity.CryptoAccount;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Getter
public class CryptoAccountStorage {
    private final Map<UUID, CryptoAccount> cryptoStorageMap = new HashMap<>();

}
