package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.scooter.api.OrderApi;
import ru.yandex.scooter.models.Order;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class OrderSteps {

    private final OrderApi orderClient = new OrderApi();

    @Step("Создать заказ")
    public Response createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    @Step("Проверить успешное создание заказа")
    public void checkOrderCreatedSuccessfully(Response response) {
        response.then().statusCode(201);
    }

    @Step("Проверить, что ответ содержит track")
    public void checkResponseContainsTrack(Response response) {
        response.then().body("track", notNullValue());
    }

    @Step("Получить список заказов")
    public Response getOrdersList() {
        return orderClient.getOrdersList();
    }

    @Step("Проверить успешное получение списка заказов")
    public void checkOrdersListRetrievedSuccessfully(Response response) {
        response.then().statusCode(200);
    }

    @Step("Проверить, что ответ содержит список заказов")
    public void checkResponseContainsOrdersList(Response response) {
        response.then().body("orders", notNullValue());
    }

    @Step("Проверить, что список заказов не пустой")
    public void checkOrdersListIsNotEmpty(Response response) {
        response.then().body("orders.size()", greaterThan(0));
    }

    @Step("Отменить заказ")
    public Response cancelOrder(int trackId) {
        return orderClient.cancelOrder(trackId);
    }

    @Step("Получить track из ответа")
    public int getTrackFromResponse(Response response) {
        return response.then().extract().path("track");
    }

    @Step("Создать тестовый заказ с цветами: {colors}")
    public Order createTestOrder(List<String> colors) {
        return new Order(
                "Иван",
                "Иванов",
                "ул. Пушкина, д. 10",
                "5",
                "+79991234567",
                3,
                "2024-12-31",
                "Тестовый комментарий",
                colors
        );
    }
}
