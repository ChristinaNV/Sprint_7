package ru.yandex.scooter.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import ru.yandex.scooter.models.Order;
import ru.yandex.scooter.steps.OrderSteps;

public class OrderTest {

    private final OrderSteps orderSteps = new OrderSteps();
    private Integer trackId;

    @After
    public void tearDown() {
        if (trackId != null) {
            orderSteps.cancelOrder(trackId);
        }
    }

    @Test
    @DisplayName("Создание заказа и проверка ответа")
    @Description("Проверка успешного создания заказа с проверкой статус кода и тела ответа")
    public void orderCanBeCreatedAndResponseContainsTrack() {
        // Given
        Order order = orderSteps.createTestOrderWithBlackColor();

        // When
        Response response = orderSteps.createOrder(order);

        // Then
        orderSteps.checkOrderCreatedSuccessfully(response); // статус код 201
        orderSteps.checkResponseContainsTrack(response);    // тело содержит track

        trackId = orderSteps.getTrackFromResponse(response);
    }

    @Test
    @DisplayName("Получение списка заказов с проверкой ответа")
    @Description("Проверка успешного получения списка заказов с проверкой статус кода и тела ответа")
    public void ordersListCanBeRetrievedWithValidResponse() {
        // When
        Response response = orderSteps.getOrdersList();

        // Then
        orderSteps.checkOrdersListRetrievedSuccessfully(response); // статус код 200
        orderSteps.checkResponseContainsOrdersList(response);      // тело содержит orders
        orderSteps.checkOrdersListIsNotEmpty(response);            // список не пустой
    }

    @Test
    @DisplayName("Создание заказа без цвета")
    @Description("Проверка создания заказа без указания цвета с полной проверкой ответа")
    public void orderCanBeCreatedWithoutColor() {
        // Given
        Order order = orderSteps.createTestOrderWithoutColor();

        // When
        Response response = orderSteps.createOrder(order);

        // Then
        orderSteps.checkOrderCreatedSuccessfully(response); // статус код 201
        orderSteps.checkResponseContainsTrack(response);    // тело содержит track

        trackId = orderSteps.getTrackFromResponse(response);
    }
}
