package com.javaacademy.cryptowallet.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api")
@Data
public class ApiProperty {
    private String baseUrl;
    private String nameHeader;
    private String valueHeader;
}
