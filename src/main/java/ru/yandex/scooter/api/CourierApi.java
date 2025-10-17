package ru.yandex.scooter.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.yandex.scooter.models.Courier;
import ru.yandex.scooter.models.CourierLogin;

import static io.restassured.RestAssured.given;

public class CourierApi {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    public Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(courier)
                .when()
                .post("/api/v1/courier");

    }

    public Response loginCourier(CourierLogin credentials) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(credentials)
                .when()
                .post("/api/v1/courier/login");
    }

    public Response deleteCourier(String courierId) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .when()
                .delete("/api/v1/courier/" + courierId);
    }

}
