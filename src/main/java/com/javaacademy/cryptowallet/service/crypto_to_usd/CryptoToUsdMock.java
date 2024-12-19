package com.javaacademy.cryptowallet.service.crypto_to_usd;

import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("local")
public class CryptoToUsdMock implements UsdRateProvider{
    @Value("${mock.crypto-value}")
    private BigDecimal mockValue;

    @Override
    public BigDecimal getRateUsd(CryptoCurrency currency) {
        return mockValue;
    }
}
