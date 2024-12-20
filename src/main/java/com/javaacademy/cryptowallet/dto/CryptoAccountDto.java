package com.javaacademy.cryptowallet.dto;

import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CryptoAccountDto {
    @Schema(description = "логин")
    private String userName;

    @Schema(description = "тип криптовалюты")
    private CryptoCurrency cryptoType;

    @Schema(description = "сумма крипты на счету")
    private BigDecimal amountOnAccount;

    @Schema(description = "id кошелька")
    private UUID uuid;
}
