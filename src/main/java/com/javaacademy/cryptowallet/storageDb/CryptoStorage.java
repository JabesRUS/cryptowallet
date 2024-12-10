package com.javaacademy.cryptowallet.storageDb;

import com.javaacademy.cryptowallet.entity.CryptoAccount;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
public class CryptoStorage {
    private final Map<UUID, CryptoAccount> cryptoStorageMap = new HashMap<>();

}
