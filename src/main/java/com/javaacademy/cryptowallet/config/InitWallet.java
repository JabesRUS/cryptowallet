package com.javaacademy.cryptowallet.config;

import com.javaacademy.cryptowallet.dto.CreateUserDto;
import com.javaacademy.cryptowallet.entity.CryptoAccount;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.entity.User;
import com.javaacademy.cryptowallet.mapper.UserMapper;
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

        cryptoAccountService.createAccount(user1.getLogin(), CryptoCurrency.BTC);
        cryptoAccountService.createAccount(user1.getLogin(), CryptoCurrency.SOL);
        cryptoAccountService.createAccount(user2.getLogin(), CryptoCurrency.ETH);


    }

}
