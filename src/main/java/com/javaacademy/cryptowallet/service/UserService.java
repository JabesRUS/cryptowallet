package com.javaacademy.cryptowallet.service;

import com.javaacademy.cryptowallet.dto.CreateUserDto;
import com.javaacademy.cryptowallet.dto.UserDto;
import com.javaacademy.cryptowallet.entity.User;
import com.javaacademy.cryptowallet.mapper.UserMapper;
import com.javaacademy.cryptowallet.repository.UserRepository;
import com.javaacademy.cryptowallet.storageDb.UserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Сохранение пользователя в хранилище 4.1
     */
    public void saveUser(CreateUserDto createUserDto) {
        User user = userMapper.convertToEntity(createUserDto);
        userRepository.saveUser(user);
    }

    /**
     * Получение пользователя по логину 4.2
     */
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login).orElseThrow();
    }

    public void resetPassword(String login, String odlPassword, String newPassword) {
        User user = getUserByLogin(login);
        String currPassword = user.getPassword();

        checkPassword(odlPassword, currPassword);

        user.setPassword(newPassword);
        log.info("Новый пароль для пользователя {} установлен", login);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(user -> userMapper.convertToDto(user))
                .toList();
    }

    private void checkPassword(String oldPass, String currentPass) {
        if (!oldPass.equals(currentPass)) {
            throw new RuntimeException("Неверно указан текущий пароль!");
        }
    }
}
