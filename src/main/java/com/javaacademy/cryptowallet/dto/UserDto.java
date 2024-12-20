package com.javaacademy.cryptowallet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    @Schema(description = "логин")
    private String login;

    @Schema(description = "почта")
    private String email;

    @Schema(description = "пароль")
    private String password;
}
