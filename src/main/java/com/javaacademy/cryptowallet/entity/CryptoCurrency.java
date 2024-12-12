package com.javaacademy.cryptowallet.entity;

import lombok.*;

@RequiredArgsConstructor
@Getter
public enum CryptoCurrency {
    BTC("bitcoin"),
    ETH("ethereum"),
    SOL("solana");
    private final String name;
}
