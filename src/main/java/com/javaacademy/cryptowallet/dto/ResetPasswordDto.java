package com.javaacademy.cryptowallet.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private final String login;
    private final String oldPassword;
    private final String newPassword;
}
