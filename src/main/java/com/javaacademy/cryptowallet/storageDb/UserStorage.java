package com.javaacademy.cryptowallet.storageDb;

import com.javaacademy.cryptowallet.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class UserStorage {
    private Map<String, User> userStorage = new HashMap<>();

    /**
     * Получение пользователя по логину 3.1.1
     */
    public Optional<User> getUserByLogin(String login) {
        return Optional.ofNullable(userStorage.get(login));
    }

    /**
     * Сохранение пользователя в хранилище 3.1.3
     */
    public void saveUser(User user) {
        String login = user.getLogin();
        if (userStorage.containsKey(login)) {
            throw new RuntimeException("Пользователь с логином %s уже существует!".formatted(login));
        }
        userStorage.put(login, user);
        log.info("Пользователь сохранен - {}", user.toString());
    }

    public Map<String, User> getUserStorage() {
        return userStorage;
    }


}
