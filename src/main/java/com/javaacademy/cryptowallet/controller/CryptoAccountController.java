package com.javaacademy.cryptowallet.controller;

import com.javaacademy.cryptowallet.dto.CreateCryptoAccountDto;
import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.dto.RefillDto;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.service.CryptoAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptowallet")
@Tag(name = "Криптосчет(CryptoAccount)", description = "Контроллер по обработке запросов по /cryptowallet")
@Slf4j
public class CryptoAccountController {
    private final CryptoAccountService cryptoAccountService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Создание нового криптосчета")
    public void createAccount(@RequestBody CreateCryptoAccountDto createCryptoAccountDto) {
        String login = createCryptoAccountDto.getUserName();
        CryptoCurrency currency = createCryptoAccountDto.getCryptoType();
        cryptoAccountService.saveAccount(login, currency);
    }

    @GetMapping()
    @Operation(summary = "Получить список всех криптосчетов по логину")
    public List<CryptoAccountDto> getAllAccountsByLogin(@RequestParam String username) {
        return cryptoAccountService.getAllAccountsByLogin(username);
    }

    @PostMapping("/refill")
    @Operation(summary = "Пополнение кошелька по id")
    public void refillAccount(@RequestBody RefillDto refillDto) {
        UUID uuid = refillDto.getUuid();
        BigDecimal amount = refillDto.getAmountRub();
        cryptoAccountService.refillAccount(uuid, amount);
        log.info("Счет пополнен на %s".formatted(amount));
    }

    @PostMapping("/withdrawal")
    @Operation(summary = "Продать крипту с кошелька по id")
    public void withdrawFromAccount(@RequestBody RefillDto refillDto) {
        UUID uuid = refillDto.getUuid();
        BigDecimal amount = refillDto.getAmountRub();
        cryptoAccountService.withdrawFromAccount(uuid, amount);
    }

    @GetMapping("/balance/{id}")
    @Operation(summary = "Получить баланс кошелька по id")
    public BigDecimal getRubAmount(@PathVariable UUID id) {
        return cryptoAccountService.getRubAmountByUuid(id);
    }

    @GetMapping("/balance")
    @Operation(summary = "Получить баланс всех кошельков в рублях по логину")
    public BigDecimal getRubAmount(@RequestParam String username) {
        return cryptoAccountService.getRubAmountAllAccountsByLogin(username);
    }
}
