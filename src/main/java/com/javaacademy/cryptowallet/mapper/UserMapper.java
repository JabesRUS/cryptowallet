package com.javaacademy.cryptowallet.mapper;

import com.javaacademy.cryptowallet.dto.UserDto;
import com.javaacademy.cryptowallet.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto convertUserToDto(User user) {
        return new UserDto(user.getLogin(),
                user.getEmail(),
                user.getPassword());
    }
}
