package com.javaacademy.cryptowallet.config;

import com.javaacademy.cryptowallet.dto.CreateUserDto;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.javaacademy.cryptowallet.service.CryptoAccountService;
import com.javaacademy.cryptowallet.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile({"local", "prod"})
public class InitWallet {
    private final UserService userService;
    private final CryptoAccountService cryptoAccountService;
    CreateUserDto user1 = new CreateUserDto("User1", "mail1@mail.ru", "11111");
//    CreateUserDto user2 = new CreateUserDto("User2", "mail2@mail.ru", "22222");

    @PostConstruct
    public void init() {
        userService.saveUser(user1);

        cryptoAccountService.saveAccount(user1.getLogin(), CryptoCurrency.BTC);
        cryptoAccountService.saveAccount(user1.getLogin(), CryptoCurrency.SOL);
//        cryptoAccountService.saveAccount(user2.getLogin(), CryptoCurrency.ETH);


    }

}
