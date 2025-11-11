package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.scooter.models.Order;

import java.util.List;

public class OrderSteps extends BaseSteps {

    private final OrderApiSteps apiSteps = new OrderApiSteps();
    private final OrderDataSteps dataSteps = new OrderDataSteps();

    @Step("Создать заказ")
    public Response createOrder(Order order) {
        return apiSteps.createOrder(order);
    }

    @Step("Получить список заказов")
    public Response getOrdersList() {
        return apiSteps.getOrdersList();
    }

    @Step("Отменить заказ")
    public Response cancelOrder(int trackId) {
        return apiSteps.cancelOrder(trackId);
    }

    @Step("Получить track из ответа")
    public int getTrackFromResponse(Response response) {
        return apiSteps.getTrackFromResponse(response);
    }

    @Step("Создать тестовый заказ с цветами: {colors}")
    public Order createTestOrder(List<String> colors) {
        return dataSteps.createTestOrder(colors);
    }

    @Step("Создать тестовый заказ с цветом BLACK")
    public Order createTestOrderWithBlackColor() {
        return dataSteps.createTestOrderWithBlackColor();
    }

    @Step("Создать тестовый заказ с цветом GREY")
    public Order createTestOrderWithGreyColor() {
        return dataSteps.createTestOrderWithGreyColor();
    }

    @Step("Создать тестовый заказ без цвета")
    public Order createTestOrderWithoutColor() {
        return dataSteps.createTestOrderWithoutColor();
    }

    // Методы проверки
    @Step("Проверить успешное создание заказа")
    public void checkOrderCreatedSuccessfully(Response response) {
        checkStatusCode201(response);
    }

    @Step("Проверить успешное получение списка заказов")
    public void checkOrdersListRetrievedSuccessfully(Response response) {
        checkStatusCode200(response);
    }
}
