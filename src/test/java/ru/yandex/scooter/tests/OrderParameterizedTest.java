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
    public class OrderParameterizedTest {

        private final OrderSteps orderSteps = new OrderSteps();
        private Integer trackId;

        private final List<String> colors;
        private final String testName;

        public OrderParameterizedTest(String testName, List<String> colors) {
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
        @DisplayName("Создание заказа с разными цветами")
        @Description("Параметризованный тест создания заказа с проверкой статус кода и тела ответа")
        public void orderCanBeCreatedWithDifferentColorsAndValidResponse() {
            // Given
            Order order = orderSteps.createTestOrder(colors);

            // When
            Response response = orderSteps.createOrder(order);

            // Then
            orderSteps.checkOrderCreatedSuccessfully(response); // статус код 201
            orderSteps.checkResponseContainsTrack(response);    // тело содержит track

            trackId = orderSteps.getTrackFromResponse(response);
        }

}
