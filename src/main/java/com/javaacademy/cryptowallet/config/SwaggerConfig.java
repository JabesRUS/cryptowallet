package com.javaacademy.cryptowallet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        Info info = new Info()
                .title("Api для криптокошелька")
                .contact(new Contact()
                        .name("Евгений")
                        .email("mail.mail.ru")
                        .url("www.www.com"))
                .description("Предоставляет возможность создать нового пользователя," +
                        " изменять пароль от аккаунта, получать всех пользователей. " +
                        "Создавать для пользователя криптокошельки и работа с ними.");
        return new OpenAPI().info(info);
    }
}
