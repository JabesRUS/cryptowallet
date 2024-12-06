package com.javaacademy.cryptowallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CryptoAccount {
    private String userLogin;
    private CryptoCurrency currency;
    private BigDecimal amountOnAccount;
    private UUID uuid;
}
