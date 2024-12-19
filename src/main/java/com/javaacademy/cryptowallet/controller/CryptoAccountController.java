package com.javaacademy.cryptowallet.controller;

import com.javaacademy.cryptowallet.dto.CreateCryptoAccountDto;
import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.dto.RefillDto;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.service.CryptoAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptowallet")
@Tag(name = "Контроллер для криптосчета")
@Slf4j
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

    @PostMapping("/refill")
    public void refillAccount(@RequestBody RefillDto refillDto) {
        UUID uuid = refillDto.getUuid();
        BigDecimal amount = refillDto.getAmountRub();
        cryptoAccountService.refillAccount(uuid, amount);
        log.info("Счет пополнен на %s".formatted(amount));
    }

    @PostMapping("/withdrawal")
    public void withdrawFromAccount(@RequestBody RefillDto refillDto) {
        UUID uuid = refillDto.getUuid();
        BigDecimal amount = refillDto.getAmountRub();
        cryptoAccountService.withdrawFromAccount(uuid, amount);
    }

    @GetMapping("/balance/{id}")
    public BigDecimal getRubAmount(@PathVariable UUID id) {
        return cryptoAccountService.getRubAmountByUuid(id);
    }

    @GetMapping("/balance")
    public BigDecimal getRubAmount(@RequestParam String username) {
        return cryptoAccountService.getRubAmountAllAccountByLogin(username);
    }
}
