package com.javaacademy.cryptowallet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.coingecko")
@Data
public class ApiProperty {
    private String baseUrl;
    private String nameHeader;
    private String valueHeader;
}
