package com.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        String env = System.getenv("ENV");
        if (env == null || env.isEmpty()) {
            env = "dev";
        }

        String configFile = String.format("config/%s.properties", env);
        properties = new Properties();

        try (InputStream input = ConfigLoader.class.getClassLoader()
                .getResourceAsStream(configFile)) {
            if (input == null) {
                throw new RuntimeException("Не найден файл: " + configFile);
            }
            properties.load(input);
            System.out.println("Загружен конфиг: " + configFile);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки: " + configFile, e);
        }
    }

    public static String getProperty(String key, String defaultValue) {
        String systemProp = System.getProperty(key);
        if (systemProp != null && !systemProp.isEmpty()) {
            return systemProp;
        }

        String propValue = properties.getProperty(key);
        if (propValue != null && !propValue.isEmpty()) {
            return propValue;
        }

        return defaultValue;
    }

    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getApiUrl() {
        return getProperty("api.url");
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static String getBrowserVersion() {
        return getProperty("browserVersion", "");
    }

    public static String getBrowserSize() {
        return getProperty("browserSize", "1920x1080");
    }
}