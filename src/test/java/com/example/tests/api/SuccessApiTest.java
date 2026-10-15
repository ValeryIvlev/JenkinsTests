package com.example.tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.config.ApiService.setupApi;
import static com.example.config.ApiService.getUser;
import static org.hamcrest.Matchers.*;

@Tag("api")
@Tag("smoke")
@Feature("API Tests")
public class SuccessApiTest {

    @BeforeAll
    static void setup() {
        setupApi();
    }

    @Test
    @DisplayName("Успешный API тест - получение пользователя")
    @Description("Тест проверяет получение данных пользователя по ID")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulGetUserTest() {
        getUser(2)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", notNullValue());
    }
}