package com.javaacademy.cryptowallet.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CryptoCurrency {
    BTC("bitcoin"),
    ETH("ethereum"),
    SOL("solana");
    private final String name;
}
