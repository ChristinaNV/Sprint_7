package ru.yandex.scooter.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.scooter.models.Courier;
import ru.yandex.scooter.models.CourierLogin;
import ru.yandex.scooter.steps.CourierSteps;

public class LoginTest {

    private final CourierSteps courierSteps = new CourierSteps();
    private String courierId;
    private Courier testCourier;

    @Before
    public void setUp() {
        testCourier = courierSteps.createUniqueTestCourier();
        courierSteps.createCourier(testCourier);
        courierId = courierSteps.getCourierId(
                new CourierLogin(testCourier.getLogin(), testCourier.getPassword())
        );
    }

    @After
    public void tearDown() {
        if (courierId != null) {
            courierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("Проверка, что курьер может авторизоваться")
    public void courierCanLogin() {
        // Given
        CourierLogin credentials = new CourierLogin(testCourier.getLogin(), testCourier.getPassword());

        // When
        Response response = courierSteps.loginCourier(credentials);

        // Then
        courierSteps.checkStatusCode200(response);
        courierSteps.checkResponseContainsId(response); // Добавляем проверку тела
    }

    @Test
    @DisplayName("Система возвращает ошибку при неверном логине")
    @Description("Проверка, что система вернёт ошибку, если неправильно указать логин")
    public void systemReturnsErrorWithWrongLogin() {
        // Given
        CourierLogin credentials = courierSteps.createCredentialsWithWrongLogin(testCourier);

        // When
        Response response = courierSteps.loginCourier(credentials);

        // Then
        courierSteps.checkStatusCode404(response);
        courierSteps.checkAccountNotFoundMessage(response); // Добавляем проверку тела
    }

    @Test
    @DisplayName("Система возвращает ошибку при неверном пароле")
    @Description("Проверка, что система вернёт ошибку, если неправильно указать пароль")
    public void systemReturnsErrorWithWrongPassword() {
        // Given
        CourierLogin credentials = courierSteps.createCredentialsWithWrongPassword(testCourier);

        // When
        Response response = courierSteps.loginCourier(credentials);

        // Then
        courierSteps.checkStatusCode404(response);
        courierSteps.checkAccountNotFoundMessage(response); // Добавляем проверку тела
    }

    @Test
    @DisplayName("Авторизация без логина возвращает ошибку")
    @Description("Проверка, что если поля логина нет, запрос возвращает ошибку")
    public void loginWithoutLoginReturnsError() {
        // Given
        CourierLogin credentials = courierSteps.createCredentialsWithoutLogin(testCourier);

        // When
        Response response = courierSteps.loginCourier(credentials);

        // Then
        courierSteps.checkStatusCode400(response);
        courierSteps.checkInsufficientDataForLoginMessage(response); // Добавляем проверку тела
    }

    @Test
    @DisplayName("Авторизация без пароля возвращает ошибку")
    @Description("Проверка, что если поля пароля нет, запрос возвращает ошибку")
    public void loginWithoutPasswordReturnsError() {
        // Given
        CourierLogin credentials = courierSteps.createCredentialsWithoutPassword(testCourier);

        // When
        Response response = courierSteps.loginCourier(credentials);

        // Then
        courierSteps.checkStatusCode400(response);
        courierSteps.checkInsufficientDataForLoginMessage(response); // Добавляем проверку тела
    }

    @Test
    @DisplayName("Авторизация под несуществующим пользователем возвращает ошибку")
    @Description("Проверка, что если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void loginWithNonExistentCourierReturnsError() {
        // Given
        CourierLogin credentials = courierSteps.createNonExistentCourierCredentials();

        // When
        Response response = courierSteps.loginCourier(credentials);

        // Then
        courierSteps.checkStatusCode404(response);
        courierSteps.checkAccountNotFoundMessage(response); // Добавляем проверку тела
    }
}
