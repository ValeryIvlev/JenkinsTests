package com.example.tests.ui;

import com.example.pages.TextBoxPage;
import com.example.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

@Tag("ui")
@Tag("smoke")
@Feature("UI Tests")
public class SuccessUITest extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @DisplayName("Успешный UI тест - проверка формы")
    @Description("Тест заполняет форму и проверяет успешную отправку")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulFormSubmitTest() {
        textBoxPage.openPage()
                .setUserName("Test User")
                .setUserEmail("test@example.com")
                .setCurrentAddress("Test Address 123")
                .submitForm()
                .checkOutputVisible()
                .checkOutputName("Test User");
    }

    @Test
    @DisplayName("Успешный UI тест - проверка формы 2")
    @Description("Тест заполняет форму и проверяет успешную отправку 2")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulFormSubmitTest2() {
        textBoxPage.openPage()
                .setUserName("Test User")
                .setUserEmail("test@example.com")
                .setCurrentAddress("Test Address 123")
                .submitForm()
                .checkOutputVisible()
                .checkOutputName("Test User");
    }
}