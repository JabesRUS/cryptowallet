package com.javaacademy.cryptowallet.service.exchange;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface RubToUsdConverter {
    public BigDecimal convertRubToUsd(BigDecimal amountRub);

    public BigDecimal convertUsdToRub(BigDecimal amountUsd);
}

