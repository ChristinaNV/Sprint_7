package ru.yandex.scooter.steps;

import io.restassured.response.Response;
import ru.yandex.scooter.api.CourierApi;
import ru.yandex.scooter.models.Courier;
import ru.yandex.scooter.models.CourierLogin;
import ru.yandex.scooter.utils.RandomGenerator;
import io.qameta.allure.Step;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierSteps {

    private final CourierApi courierClient = new CourierApi();

    @Step("Создать курьера")
    public Response createCourier(Courier courier) {
        return courierClient.createCourier(courier);
    }

    @Step("Проверить код ответа 201")
    public void checkStatusCode201(Response response) {
        response.then().statusCode(201);
    }

    @Step("Проверить код ответа 200")
    public void checkStatusCode200(Response response) {
        response.then().statusCode(200);
    }

    @Step("Проверить код ответа 400")
    public void checkStatusCode400(Response response) {
        response.then().statusCode(400);
    }

    @Step("Проверить код ответа 404")
    public void checkStatusCode404(Response response) {
        response.then().statusCode(404);
    }

    @Step("Проверить код ответа 409")
    public void checkStatusCode409(Response response) {
        response.then().statusCode(409);
    }

    @Step("Проверить, что ответ содержит ok: true")
    public void checkResponseContainsOkTrue(Response response) {
        response.then().body("ok", equalTo(true));
    }

    @Step("Проверить, что ответ содержит id")
    public void checkResponseContainsId(Response response) {
        response.then().body("id", notNullValue());
    }

    @Step("Проверить сообщение о дубликате логина")
    public void checkDuplicateLoginMessage(Response response) {
        response.then().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Проверить сообщение о недостатке данных для создания")
    public void checkInsufficientDataForCreationMessage(Response response) {
        response.then().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Проверить сообщение о недостатке данных для входа")
    public void checkInsufficientDataForLoginMessage(Response response) {
        response.then().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Проверить сообщение о не найденной учетной записи")
    public void checkAccountNotFoundMessage(Response response) {
        response.then().body("message", equalTo("Учетная запись не найдена"));
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

    @Step("Создать уникального тестового курьера")
    public Courier createUniqueTestCourier() {
        return new Courier(
                RandomGenerator.generateRandomLogin(),
                RandomGenerator.generateRandomPassword(),
                RandomGenerator.generateRandomFirstName()
        );
    }

    @Step("Создать курьера без логина")
    public Courier createCourierWithoutLogin() {
        return new Courier(
                null,
                RandomGenerator.generateRandomPassword(),
                RandomGenerator.generateRandomFirstName()
        );
    }

    @Step("Создать курьера без пароля")
    public Courier createCourierWithoutPassword() {
        return new Courier(
                RandomGenerator.generateRandomLogin(),
                null,
                RandomGenerator.generateRandomFirstName()
        );
    }

    @Step("Создать курьера без имени")
    public Courier createCourierWithoutFirstName() {
        return new Courier(
                RandomGenerator.generateRandomLogin(),
                RandomGenerator.generateRandomPassword(),
                null
        );
    }

    @Step("Создать учетные данные с неверным логином")
    public CourierLogin createCredentialsWithWrongLogin(Courier courier) {
        return new CourierLogin("wrong_" + courier.getLogin(), courier.getPassword());
    }

    @Step("Создать учетные данные с неверным паролем")
    public CourierLogin createCredentialsWithWrongPassword(Courier courier) {
        return new CourierLogin(courier.getLogin(), "wrong_" + courier.getPassword());
    }

    @Step("Создать учетные данные без логина")
    public CourierLogin createCredentialsWithoutLogin(Courier courier) {
        return new CourierLogin(null, courier.getPassword());
    }

    @Step("Создать учетные данные без пароля")
    public CourierLogin createCredentialsWithoutPassword(Courier courier) {
        return new CourierLogin(courier.getLogin(), null);
    }

    @Step("Создать учетные данные несуществующего курьера")
    public CourierLogin createNonExistentCourierCredentials() {
        return new CourierLogin(
                RandomGenerator.generateRandomLogin(),
                RandomGenerator.generateRandomPassword()
        );
    }
}
