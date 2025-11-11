package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import ru.yandex.scooter.models.Courier;
import ru.yandex.scooter.models.CourierLogin;
import ru.yandex.scooter.utils.TestDataGenerator;

public class CourierDataSteps {

    @Step("Создать уникального тестового курьера")
    public Courier createUniqueTestCourier() {
        return new Courier(
                TestDataGenerator.generateCourierLogin(),
                TestDataGenerator.generateCourierPassword(),
                TestDataGenerator.generateCourierFirstName()
        );
    }

    @Step("Создать курьера без логина")
    public Courier createCourierWithoutLogin() {
        return new Courier(
                null,
                TestDataGenerator.generateCourierPassword(),
                TestDataGenerator.generateCourierFirstName()
        );
    }

    @Step("Создать курьера без пароля")
    public Courier createCourierWithoutPassword() {
        return new Courier(
                TestDataGenerator.generateCourierLogin(),
                null,
                TestDataGenerator.generateCourierFirstName()
        );
    }

    @Step("Создать курьера без имени")
    public Courier createCourierWithoutFirstName() {
        return new Courier(
                TestDataGenerator.generateCourierLogin(),
                TestDataGenerator.generateCourierPassword(),
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
                TestDataGenerator.generateCourierLogin(),
                TestDataGenerator.generateCourierPassword()
        );
    }
}
