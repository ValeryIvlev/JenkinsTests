package com.example.tests.ui;

import com.example.pages.TextBoxPage;
import com.example.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

@Tag("ui")
@Tag("regression")
@Feature("UI Tests")
public class FailUITest extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @DisplayName("Падающий UI тест - несуществующий элемент")
    @Description("Тест специально падает для демонстрации отчета")
    @Severity(SeverityLevel.NORMAL)
    public void failingUITest() {
        textBoxPage.openPage().checkNonExistentElementText("123");
    }
}