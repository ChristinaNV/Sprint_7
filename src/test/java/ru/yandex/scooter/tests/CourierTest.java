package ru.yandex.scooter.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import ru.yandex.scooter.models.Courier;
import ru.yandex.scooter.models.CourierLogin;
import ru.yandex.scooter.steps.CourierSteps;

public class CourierTest {

    private final CourierSteps courierSteps = new CourierSteps();
    private String courierId;
    private Courier testCourier;

    @After
    public void tearDown() {
        if (courierId != null) {
            courierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Курьера можно создать")
    @Description("Проверка, что курьера можно создать")
    public void courierCanBeCreated() {
        // Given
        testCourier = courierSteps.createUniqueTestCourier();

        // When
        Response response = courierSteps.createCourier(testCourier);

        // Then
        courierSteps.checkStatusCode201(response);
        courierSteps.checkResponseContainsOkTrue(response);

        courierId = courierSteps.getCourierId(
                new CourierLogin(testCourier.getLogin(), testCourier.getPassword())
        );
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    @Description("Проверка, что нельзя создать двух одинаковых курьеров")
    public void cannotCreateTwoIdenticalCouriers() {
        // Given
        testCourier = courierSteps.createUniqueTestCourier();
        courierSteps.createCourier(testCourier);
        courierId = courierSteps.getCourierId(
                new CourierLogin(testCourier.getLogin(), testCourier.getPassword())
        );

        // When
        Response response = courierSteps.createCourier(testCourier);

        // Then
        courierSteps.checkStatusCode409(response);
        courierSteps.checkDuplicateLoginMessage(response);
    }

    @Test
    @DisplayName("Создание курьера без логина возвращает ошибку")
    @Description("Проверка, что если поля логина нет, запрос возвращает ошибку")
    public void createCourierWithoutLoginReturnsError() {
        // Given
        Courier courier = courierSteps.createCourierWithoutLogin();

        // When
        Response response = courierSteps.createCourier(courier);

        // Then
        courierSteps.checkStatusCode400(response);
        courierSteps.checkInsufficientDataForCreationMessage(response);
    }

    @Test
    @DisplayName("Создание курьера без пароля возвращает ошибку")
    @Description("Проверка, что если поля пароля нет, запрос возвращает ошибку")
    public void createCourierWithoutPasswordReturnsError() {
        // Given
        Courier courier = courierSteps.createCourierWithoutPassword();

        // When
        Response response = courierSteps.createCourier(courier);

        // Then
        courierSteps.checkStatusCode400(response);
        courierSteps.checkInsufficientDataForCreationMessage(response);
    }

    @Test
    @DisplayName("Создание курьера без имени возможно")
    @Description("Проверка, что имя не является обязательным полем")
    public void createCourierWithoutFirstNameIsPossible() {
        // Given
        Courier courier = courierSteps.createCourierWithoutFirstName();

        // When
        Response response = courierSteps.createCourier(courier);

        // Then
        courierSteps.checkStatusCode201(response);
        courierSteps.checkResponseContainsOkTrue(response);

        courierId = courierSteps.getCourierId(
                new CourierLogin(courier.getLogin(), courier.getPassword())
        );
    }

}
