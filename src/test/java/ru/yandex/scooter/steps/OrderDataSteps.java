package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import ru.yandex.scooter.models.Order;

import java.util.List;

public class OrderDataSteps {

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

    @Step("Создать тестовый заказ с цветом BLACK")
    public Order createTestOrderWithBlackColor() {
        return createTestOrder(List.of("BLACK"));
    }

    @Step("Создать тестовый заказ с цветом GREY")
    public Order createTestOrderWithGreyColor() {
        return createTestOrder(List.of("GREY"));
    }

    @Step("Создать тестовый заказ без цвета")
    public Order createTestOrderWithoutColor() {
        return createTestOrder(null);
    }

}
