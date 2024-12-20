package com.javaacademy.cryptowallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RefillDto {
    @JsonProperty("account_id")
    @Schema(description = "id кошелька")
    private UUID uuid;

    @JsonProperty("rubles_amount")
    @Schema(description = "сумма пополнения в рублях")
    private BigDecimal amountRub;
}
