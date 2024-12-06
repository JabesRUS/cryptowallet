package com.javaacademy.cryptowallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoAccount {
    private String userLogin;
    private CryptoCurrency currency;
    private BigDecimal amountOnAccount;
    private UUID uuid;
}
