package com.javaacademy.cryptowallet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDto {
    @Schema(description = "логин")
    private String login;
    @Schema(description = "почта")
    private String email;
    @Schema(description = "пароль")
    private String password;
}
