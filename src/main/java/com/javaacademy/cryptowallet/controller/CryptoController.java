package com.javaacademy.cryptowallet.controller;

import com.javaacademy.cryptowallet.dto.CryptoDto;
import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.service.CryptoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptowallet")
public class CryptoController {
    private final CryptoService cryptoService;

    @PostMapping
    public String createAccount(@RequestBody CryptoDto cryptoDto) {
        String login = cryptoDto.getUserName();
        CryptoCurrency currency = cryptoDto.getCryptoType();
        UUID uuid = cryptoService.createAccount(login, currency);

        return "Счет создан. \nВаш UUID: %s".formatted(uuid.toString());
    }

    @GetMapping()
    public List<CryptoAccount> getAllAccountsByLogin(@RequestParam String username) {
        return cryptoService.getAllAccountsByLogin(username);
    }
}
