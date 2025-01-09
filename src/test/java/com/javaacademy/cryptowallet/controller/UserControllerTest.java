package com.javaacademy.cryptowallet.controller;

import com.javaacademy.cryptowallet.dto.CreateUserDto;
import com.javaacademy.cryptowallet.dto.ResetPasswordDto;
import com.javaacademy.cryptowallet.dto.UserDto;
import com.javaacademy.cryptowallet.entity.User;
import com.javaacademy.cryptowallet.service.UserService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("local")
class UserControllerTest {
    @Autowired
    private UserService userService;

    private RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBasePath("/user")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
    @Test
    @DisplayName("Создание нового пользователя")
    public void createUserSuccess() {
        CreateUserDto createdUser = new CreateUserDto("User3", "mail3@mail.ru", "33333");
        RestAssured.given(requestSpecification)
                .body(createdUser)
                .post("/singup")
                .then()
                .spec(responseSpecification)
                .statusCode(201);

        Assertions.assertDoesNotThrow(() -> userService.getUserByLogin(createdUser.getLogin()));

        User newUser = userService.getUserByLogin(createdUser.getLogin());
        Assertions.assertEquals(createdUser.getEmail(), newUser.getEmail());
        Assertions.assertEquals(createdUser.getPassword(), newUser.getPassword());
    }

    @Test
    @DisplayName("Сброс пароля")
    public void resetPasswordSuccess() {
        String userName = "User1";
        String oldPassword = userService.getUserByLogin(userName).getPassword();
        String newPassword = "12345";
        ResetPasswordDto resetPasswordDto= new ResetPasswordDto(userName, oldPassword, newPassword);

        RestAssured.given(requestSpecification)
                .body(resetPasswordDto)
                .post("/reset-password")
                .then()
                .spec(responseSpecification)
                .statusCode(202);

        Assertions.assertEquals(newPassword, userService.getUserByLogin(userName).getPassword());
    }

    @Test
    @DisplayName("Получение всех учителей")
    public void getAll() {
        List<UserDto> listUsers = RestAssured.given(requestSpecification)
                .get("/getall")
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        Assertions.assertEquals(1, listUsers.size());

        UserDto userDto = listUsers.get(0);
        Assertions.assertEquals("User1", userDto.getLogin());
        Assertions.assertEquals("mail1@mail.ru", userDto.getEmail());
        Assertions.assertEquals("11111", userDto.getPassword());
    }
}