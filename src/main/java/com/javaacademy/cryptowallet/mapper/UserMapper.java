package com.javaacademy.cryptowallet.mapper;

import com.javaacademy.cryptowallet.dto.CreateUserDto;
import com.javaacademy.cryptowallet.dto.UserDto;
import com.javaacademy.cryptowallet.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User convertToEntity(CreateUserDto createUserDto) {
        return new User(createUserDto.getLogin(),
                createUserDto.getEmail(),
                createUserDto.getPassword());
    }

    public UserDto convertToDto(User user) {
        return new UserDto(user.getLogin(),
                user.getEmail(),
                user.getPassword());
    }
}
