package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class BaseSteps {

    @Step("Проверить код ответа 201")
    public void checkStatusCode201(Response response) {
        response.then().statusCode(SC_CREATED);
    }

    @Step("Проверить код ответа 200")
    public void checkStatusCode200(Response response) {
        response.then().statusCode(SC_OK);
    }

    @Step("Проверить код ответа 400")
    public void checkStatusCode400(Response response) {
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @Step("Проверить код ответа 404")
    public void checkStatusCode404(Response response) {
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Step("Проверить код ответа 409")
    public void checkStatusCode409(Response response) {
        response.then().statusCode(SC_CONFLICT);
    }

    @Step("Проверить, что ответ содержит ok: true")
    public void checkResponseContainsOkTrue(Response response) {
        response.then().body("ok", equalTo(true));
    }

    @Step("Проверить, что ответ содержит id")
    public void checkResponseContainsId(Response response) {
        response.then().body("id", notNullValue());
    }

    @Step("Проверить сообщение о дубликате логина")
    public void checkDuplicateLoginMessage(Response response) {
        response.then().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Проверить сообщение о недостатке данных для создания")
    public void checkInsufficientDataForCreationMessage(Response response) {
        response.then().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Проверить сообщение о недостатке данных для входа")
    public void checkInsufficientDataForLoginMessage(Response response) {
        response.then().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Проверить сообщение о не найденной учетной записи")
    public void checkAccountNotFoundMessage(Response response) {
        response.then().body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Проверить, что ответ содержит track")
    public void checkResponseContainsTrack(Response response) {
        response.then().body("track", notNullValue());
    }

    @Step("Проверить, что ответ содержит список заказов")
    public void checkResponseContainsOrdersList(Response response) {
        response.then().body("orders", notNullValue());
    }

    @Step("Проверить, что список заказов не пустой")
    public void checkOrdersListIsNotEmpty(Response response) {
        response.then().body("orders.size()", greaterThan(0));
    }

}
