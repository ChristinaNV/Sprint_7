package ru.yandex.scooter.api;

import io.restassured.response.Response;
import ru.yandex.scooter.models.Order;

import static io.restassured.RestAssured.given;

public class OrderApi extends BaseApi {
    private static final String ORDERS_ENDPOINT = "/api/v1/orders";
    private static final String CANCEL_ORDER_ENDPOINT = "/api/v1/orders/cancel";

    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    public Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .when()
                .get(ORDERS_ENDPOINT);
    }

    public Response cancelOrder(int trackId) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body("{\"track\": " + trackId + "}")
                .when()
                .put(CANCEL_ORDER_ENDPOINT);
    }
}