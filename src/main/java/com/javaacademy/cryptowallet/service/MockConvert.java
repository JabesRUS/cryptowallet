package com.javaacademy.cryptowallet.service;

import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("local")
public class MockConvert {

    @Value("${mock.value}")
    private BigDecimal mockValue;

    public BigDecimal getMockRate(CryptoCurrency currency) {
        return mockValue;
    }

}
