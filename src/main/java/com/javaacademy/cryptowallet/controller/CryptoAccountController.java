package com.javaacademy.cryptowallet.controller;

import com.javaacademy.cryptowallet.dto.CreateCryptoAccountDto;
import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.service.CryptoAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptowallet")
public class CryptoAccountController {
    private final CryptoAccountService cryptoAccountService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createAccount(@RequestBody CreateCryptoAccountDto createCryptoAccountDto) {
        String login = createCryptoAccountDto.getUserName();
        CryptoCurrency currency = createCryptoAccountDto.getCryptoType();
        cryptoAccountService.createAccount(login, currency);
    }

    @GetMapping()
    public List<CryptoAccountDto> getAllAccountsByLogin(@RequestParam String username) {
        return cryptoAccountService.getAllAccountsByLogin(username);
    }
}
