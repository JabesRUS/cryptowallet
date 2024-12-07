package com.javaacademy.cryptowallet.repository;

import com.javaacademy.cryptowallet.entity.User;
import com.javaacademy.cryptowallet.storageDb.UserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepository {
    private final UserStorage userStorage;

    /**
     * Получение пользователя по логину 3.1.1
     */
    public Optional<User> getUserByLogin(String login) {
        return userStorage.getUserByLogin(login);
    }

    /**
     * Сохранение пользователя в хранилище 3.1.3
     */
    public void saveUser(User user) {
        userStorage.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getUserStorage().values().stream().toList();
    }
}
