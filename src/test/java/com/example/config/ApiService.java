package com.example.config;

import com.example.config.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiService {

    private static final String API_KEY = "reqres-free-v1";

    public static void setupApi() {
        RequestSpecification apiSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getApiUrl())
                .addHeader("x-api-key", API_KEY)
                .build();
        RestAssured.requestSpecification = apiSpec;
    }

    public static Response getUser(int id) {
        return RestAssured
                .given()
                .log().all()
                .when()
                .get("/users/" + id);
    }

    public static Response getNonExistentUser(int id) {
        return RestAssured
                .given()
                .log().all()
                .when()
                .get("/users/" + id);
    }
}
