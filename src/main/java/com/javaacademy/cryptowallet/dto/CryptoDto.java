package com.javaacademy.cryptowallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CryptoDto {
    @JsonProperty("username")
    private String userName;
    @JsonProperty("crypto_type")
    private CryptoCurrency cryptoType;
}
