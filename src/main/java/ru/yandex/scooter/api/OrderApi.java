package ru.yandex.scooter.api;

import io.restassured.response.Response;
import ru.yandex.scooter.models.Order;

import static io.restassured.RestAssured.given;

public class OrderApi {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    public Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .when()
                .get("/api/v1/orders");
    }

    public Response cancelOrder(int trackId) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body("{\"track\": " + trackId + "}")
                .when()
                .put("/api/v1/orders/cancel");
    }

}
