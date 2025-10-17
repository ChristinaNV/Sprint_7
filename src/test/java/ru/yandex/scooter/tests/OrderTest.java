package ru.yandex.scooter.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.scooter.models.Order;
import ru.yandex.scooter.steps.OrderSteps;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class OrderTest {

    private final OrderSteps orderSteps = new OrderSteps();
    private Integer trackId;

    private final List<String> colors;
    private final String testName;

    public OrderTest(String testName, List<String> colors) {
        this.testName = testName;
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Можно указать BLACK цвет", Arrays.asList("BLACK")},
                {"Можно указать GREY цвет", Arrays.asList("GREY")},
                {"Можно указать оба цвета", Arrays.asList("BLACK", "GREY")},
                {"Можно совсем не указывать цвет", null}
        });
    }

    @After
    public void tearDown() {
        if (trackId != null) {
            orderSteps.cancelOrder(trackId);
        }
    }

    @Test
    @DisplayName("Создание заказа с разными цветами - параметризованный тест")
    @Description("Параметризованный тест создания заказа: можно указать BLACK, GREY, оба цвета или не указывать цвет")
    public void orderCanBeCreatedWithDifferentColors() {
        // Given
        Order order = orderSteps.createTestOrder(colors);

        // When
        Response response = orderSteps.createOrder(order);

        // Then
        orderSteps.checkOrderCreatedSuccessfully(response);

        trackId = orderSteps.getTrackFromResponse(response);
    }

    @Test
    @DisplayName("Тело ответа при создании заказа содержит track")
    @Description("Проверка, что тело ответа содержит track")
    public void responseBodyContainsTrack() {
        // Given
        Order order = orderSteps.createTestOrder(Arrays.asList("BLACK"));

        // When
        Response response = orderSteps.createOrder(order);

        // Then
        orderSteps.checkResponseContainsTrack(response);

        trackId = orderSteps.getTrackFromResponse(response);
    }

    @Test
    @DisplayName("В тело ответа возвращается список заказов")
    @Description("Проверка, что в тело ответа возвращается список заказов")
    public void responseBodyContainsOrdersList() {
        // When
        Response response = orderSteps.getOrdersList();

        // Then
        orderSteps.checkResponseContainsOrdersList(response);
    }

    @Test
    @DisplayName("Список заказов не пустой")
    @Description("Проверка, что возвращаемый список заказов содержит элементы")
    public void ordersListIsNotEmpty() {
        // When
        Response response = orderSteps.getOrdersList();

        // Then
        orderSteps.checkOrdersListIsNotEmpty(response);
    }

    @Test
    @DisplayName("Успешное получение списка заказов")
    @Description("Проверка успешного получения списка заказов")
    public void ordersListCanBeRetrievedSuccessfully() {
        // When
        Response response = orderSteps.getOrdersList();

        // Then
        orderSteps.checkOrdersListRetrievedSuccessfully(response);
    }

}
