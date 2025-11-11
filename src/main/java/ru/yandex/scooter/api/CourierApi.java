package ru.yandex.scooter.api;

import io.restassured.response.Response;
import ru.yandex.scooter.models.Courier;
import ru.yandex.scooter.models.CourierLogin;

import static io.restassured.RestAssured.given;

public class CourierApi extends BaseApi {

    private static final String COURIER_ENDPOINT = "/api/v1/courier";
    private static final String LOGIN_ENDPOINT = "/api/v1/courier/login";

    public Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(courier)
                .when()
                .post(COURIER_ENDPOINT);

    }

    public Response loginCourier(CourierLogin credentials) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(credentials)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    public Response deleteCourier(String courierId) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .when()
                .delete(COURIER_ENDPOINT + courierId);
    }
}
