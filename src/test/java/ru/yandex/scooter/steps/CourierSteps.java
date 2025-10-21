package ru.yandex.scooter.steps;

import io.restassured.response.Response;
import ru.yandex.scooter.api.CourierApi;
import ru.yandex.scooter.models.Courier;
import ru.yandex.scooter.models.CourierLogin;
import io.qameta.allure.Step;

public class CourierSteps extends BaseSteps {

    private final CourierApi courierClient = new CourierApi();
    private final CourierDataSteps dataSteps = new CourierDataSteps();

    @Step("Создать курьера")
    public Response createCourier(Courier courier) {
        return courierClient.createCourier(courier);
    }

    @Step("Авторизовать курьера")
    public Response loginCourier(CourierLogin credentials) {
        return courierClient.loginCourier(credentials);
    }

    @Step("Удалить курьера")
    public Response deleteCourier(String courierId) {
        return courierClient.deleteCourier(courierId);
    }

    @Step("Получить ID курьера")
    public String getCourierId(CourierLogin credentials) {
        Response loginResponse = loginCourier(credentials);
        checkStatusCode200(loginResponse);
        return loginResponse.then().extract().path("id").toString();
    }

    // Делегируем методы работы с данными
    public Courier createUniqueTestCourier() {
        return dataSteps.createUniqueTestCourier();
    }

    public Courier createCourierWithoutLogin() {
        return dataSteps.createCourierWithoutLogin();
    }

    public Courier createCourierWithoutPassword() {
        return dataSteps.createCourierWithoutPassword();
    }

    public Courier createCourierWithoutFirstName() {
        return dataSteps.createCourierWithoutFirstName();
    }

    public CourierLogin createCredentialsWithWrongLogin(Courier courier) {
        return dataSteps.createCredentialsWithWrongLogin(courier);
    }

    public CourierLogin createCredentialsWithWrongPassword(Courier courier) {
        return dataSteps.createCredentialsWithWrongPassword(courier);
    }

    public CourierLogin createCredentialsWithoutLogin(Courier courier) {
        return dataSteps.createCredentialsWithoutLogin(courier);
    }

    public CourierLogin createCredentialsWithoutPassword(Courier courier) {
        return dataSteps.createCredentialsWithoutPassword(courier);
    }

    public CourierLogin createNonExistentCourierCredentials() {
        return dataSteps.createNonExistentCourierCredentials();
    }
}
