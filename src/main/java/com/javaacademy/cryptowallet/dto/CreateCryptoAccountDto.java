package com.javaacademy.cryptowallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCryptoAccountDto {
    @JsonProperty("username")
    @Schema(description = "логин")
    private String userName;
    @JsonProperty("crypto_type")
    @Schema(description = "тип криптовалюты")
    private CryptoCurrency cryptoType;
}
