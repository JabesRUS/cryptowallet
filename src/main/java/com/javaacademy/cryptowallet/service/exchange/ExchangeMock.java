package com.javaacademy.cryptowallet.service.exchange;

import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Profile("local")
public class ExchangeMock implements RubToUsdConverter{
    @Value("${mock.usd-value}")
    private BigDecimal mockUsdValue;

    @Override
    public BigDecimal convertRubToUsd(BigDecimal amountRub) {
        BigDecimal rateDollar = mockUsdValue;
        return amountRub.divide(rateDollar, 2, RoundingMode.HALF_DOWN);
    }

    @Override
    public BigDecimal convertUsdToRub(BigDecimal amountUsd) {
        BigDecimal rateDollar = mockUsdValue;
        return amountUsd.multiply(rateDollar);
    }

}
