package com.javaacademy.cryptowallet.controller;

import com.javaacademy.cryptowallet.dto.CreateUserDto;
import com.javaacademy.cryptowallet.dto.ResetPasswordDto;
import com.javaacademy.cryptowallet.dto.UserDto;
import com.javaacademy.cryptowallet.service.UserService;
import com.javaacademy.cryptowallet.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/singup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void singUpUser(@RequestBody CreateUserDto createUserDto) {
        userService.saveUser(createUserDto);
    }

    @PostMapping("/reset-password")
    public void singUpUser(@RequestBody ResetPasswordDto resetPasswordDto) {
        userService.resetPassword(resetPasswordDto.getLogin(),
                resetPasswordDto.getOldPassword(),
                resetPasswordDto.getNewPassword());
    }

    @GetMapping("/getall")
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

}
