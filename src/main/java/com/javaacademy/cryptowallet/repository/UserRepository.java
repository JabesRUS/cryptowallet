package com.javaacademy.cryptowallet.repository;

import com.javaacademy.cryptowallet.entity.User;
import com.javaacademy.cryptowallet.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepository {
    private final UserStorage userStorage;

    public Optional<User> getUserByLogin(String login) {
        return Optional.ofNullable(userStorage.getUserStorage().get(login));
    }

    public void saveUser(User user) {
        String login = user.getLogin();
        if (userStorage.getUserStorage().containsKey(login)) {
            throw new RuntimeException("Пользователь с логином %s уже существует!".formatted(login));
        }
        userStorage.getUserStorage().put(login, user);
        log.info("Пользователь сохранен - {}", user.toString());
    }

    public List<User> getAllUsers() {
        return userStorage.getUserStorage().values().stream().toList();
    }
}
