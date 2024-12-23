package com.javaacademy.cryptowallet.storage;

import com.javaacademy.cryptowallet.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
@Getter
public class UserStorage {
    private final Map<String, User> userStorage = new HashMap<>();
}
