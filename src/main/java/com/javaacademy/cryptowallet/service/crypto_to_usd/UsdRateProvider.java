package com.javaacademy.cryptowallet.service.crypto_to_usd;

import com.javaacademy.cryptowallet.entity.CryptoCurrency;

import java.math.BigDecimal;

public interface UsdRateProvider {
    public BigDecimal getRateUsd(CryptoCurrency currency);
}
