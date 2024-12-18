package com.javaacademy.cryptowallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RefillDto {
    @JsonProperty("account_id")
    private UUID uuid;
    @JsonProperty("rubles_amount")
    private BigDecimal amountRub;
}
