package com.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.config.ConfigLoader;
import com.example.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        configureSelenide();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    private static void configureSelenide() {
        Configuration.browser = ConfigLoader.getBrowser();
        Configuration.browserVersion = ConfigLoader.getBrowserVersion();
        Configuration.browserSize = ConfigLoader.getBrowserSize();
        Configuration.baseUrl = ConfigLoader.getBaseUrl();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = Long.parseLong(
                ConfigLoader.getProperty("selenide.timeout", "10000")
        );
        Configuration.headless = Boolean.parseBoolean(
                ConfigLoader.getProperty("browser.headless", "false")
        );

//        String remoteUrl = ConfigLoader.getProperty("remote.url", "");
        String remoteUrl = "";
        if (!remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;

            System.out.println("Selenoid настроен: " + remoteUrl);
        } else {
            System.out.println("запуск локально (без Selenoid)");
        }

        System.out.println("   Selenide настроен:");
        System.out.println("   ENV: " + System.getenv("ENV"));
        System.out.println("   Base URL: " + Configuration.baseUrl);
        System.out.println("   Browser: " + Configuration.browser + " " + Configuration.browserVersion);
        System.out.println("   Size: " + Configuration.browserSize);
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        String selenoidUrl = ConfigLoader.getProperty("selenoid.url", "");
        if (!selenoidUrl.isEmpty()) {
            Attach.addVideo();
        }

        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWebDriver();
    }
}