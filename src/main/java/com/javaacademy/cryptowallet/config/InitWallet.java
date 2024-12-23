package com.javaacademy.cryptowallet.config;

import com.javaacademy.cryptowallet.dto.CreateUserDto;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.service.CryptoAccountService;
import com.javaacademy.cryptowallet.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitWallet {
    private final UserService userService;
    private final CryptoAccountService cryptoAccountService;
    CreateUserDto user1 = new CreateUserDto("User1", "qwe@qwe.ru", "11111");
    CreateUserDto user2 = new CreateUserDto("User2", "asdf@qwe.ru", "22222");

    @PostConstruct
    public void init() {
        userService.saveUser(user1);
        userService.saveUser(user2);

        cryptoAccountService.saveAccount(user1.getLogin(), CryptoCurrency.BTC);
        cryptoAccountService.saveAccount(user1.getLogin(), CryptoCurrency.SOL);
        cryptoAccountService.saveAccount(user2.getLogin(), CryptoCurrency.ETH);


    }

}
