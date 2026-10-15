package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$; 
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private final SelenideElement userNameField = $("#userName");
    private final SelenideElement userEmailField = $("#userEmail");
    private final SelenideElement currentAddressField = $("#currentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputDiv = $("#output");
    private final SelenideElement nameFieldOutput = $("#name");
    public final SelenideElement nonExistentElement = $("#id");

    @Step("Открыть страницу /text-box")
    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    @Step("Ввести имя пользователя: {userName}")
    public TextBoxPage setUserName(String userName) {
        userNameField.setValue(userName);
        return this;
    }

    @Step("Ввести email: {userEmail}")
    public TextBoxPage setUserEmail(String userEmail) {
        userEmailField.setValue(userEmail);
        return this;
    }

    @Step("Ввести текущий адрес: {currentAddress}")
    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressField.setValue(currentAddress);
        return this;
    }

    @Step("Нажать кнопку Отправить")
    public TextBoxPage submitForm() {
        submitButton.click();
        return this;
    }

    @Step("Проверить, что блок вывода видим")
    public TextBoxPage checkOutputVisible() {
        outputDiv.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что имя в выводе соответствует: {expectedName}")
    public TextBoxPage checkOutputName(String expectedName) {
        nameFieldOutput.shouldHave(text(expectedName));
        return this;
    }

    @Step("Проверить текст несуществующего элемента на соответствие: {expectedText}")
    public TextBoxPage checkNonExistentElementText(String expectedText) {
        nonExistentElement.shouldHave(text(expectedText));
        return this;
    }
}
