package com.javaacademy.cryptowallet.service;

import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Profile("local")
public class MockConvert {

    @Value("${mock.crypto-value}")
    private BigDecimal mockValue;
    @Value("${mock.usd-value}")
    private BigDecimal mockUsdValue;

    public BigDecimal getMockRate(CryptoCurrency currency) {
        return mockValue;
    }

    public BigDecimal convertRubToUsd(BigDecimal amountRub) throws IOException {
        BigDecimal rateDollar = mockUsdValue;
        return amountRub.divide(rateDollar, 2, RoundingMode.HALF_DOWN);
    }

    public BigDecimal convertUsdToRub(BigDecimal amountUsd) throws IOException {
        BigDecimal rateDollar = mockUsdValue;
        return amountUsd.multiply(rateDollar);
    }

}
