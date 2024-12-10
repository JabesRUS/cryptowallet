package com.javaacademy.cryptowallet.dto;

import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoAccountDto {

    private String userName;
    private CryptoCurrency cryptoType;
    private BigDecimal amountOnAccount;
    private UUID uuid;
}
