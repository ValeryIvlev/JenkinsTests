package com.example.tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.config.ApiService.setupApi;
import static com.example.config.ApiService.getNonExistentUser;
import static org.hamcrest.Matchers.equalTo;

@Tag("api")
@Tag("regression")
@Tag("serial")
@Feature("API Tests")
public class FailApiTest {

    @BeforeAll
    static void setup() {
        setupApi();
    }

    @Test
    @DisplayName("Падающий API тест - несуществующий endpoint")
    @Description("Тест специально падает для демонстрации отчета")
    @Severity(SeverityLevel.NORMAL)
    void failingApiTest() {
        getNonExistentUser(999999)
                .then()
                .log().all()
                .statusCode(200) // 👈 Специально неправильный код (должен быть 404)
                .body("data.id", equalTo(999999));
    }
}