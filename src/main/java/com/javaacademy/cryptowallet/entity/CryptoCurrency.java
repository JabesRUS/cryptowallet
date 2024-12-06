package com.javaacademy.cryptowallet.entity;

import lombok.*;

@RequiredArgsConstructor
@Getter
public enum CryptoCurrency {
    BTC("bitcoin"),
    ETH("etherium"),
    SOL("solana");
    private final String name;
}
