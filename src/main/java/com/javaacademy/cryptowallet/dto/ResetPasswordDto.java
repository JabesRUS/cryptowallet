package com.javaacademy.cryptowallet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordDto {
    @Schema(description = "логин")
    private final String login;

    @Schema(description = "старый пароль")
    private final String oldPassword;

    @Schema(description = "новый пароль")
    private final String newPassword;
}
