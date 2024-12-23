package com.javaacademy.cryptowallet.service.exchange;

import java.math.BigDecimal;

public interface RubToUsdConverter {
    BigDecimal convertRubToUsd(BigDecimal amountRub);

    BigDecimal convertUsdToRub(BigDecimal amountUsd);
}

