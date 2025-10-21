package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.scooter.api.OrderApi;
import ru.yandex.scooter.models.Order;

public class OrderApiSteps {

    private final OrderApi orderClient = new OrderApi();

    @Step("Создать заказ")
    public Response createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    @Step("Получить список заказов")
    public Response getOrdersList() {
        return orderClient.getOrdersList();
    }

    @Step("Отменить заказ")
    public Response cancelOrder(int trackId) {
        return orderClient.cancelOrder(trackId);
    }

    @Step("Получить track из ответа")
    public int getTrackFromResponse(Response response) {
        return response.then().extract().path("track");
    }

}
