package ru.yandex.scooter.utils;


import com.github.javafaker.Faker;
import java.util.Locale;

public class TestDataGenerator {

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String generateCourierLogin() {
        return "courier_" + faker.random().hex(10);
    }

    public static String generateCourierPassword() {
        return faker.internet().password(10, 15);
    }

    public static String generateCourierFirstName() {
        return faker.name().firstName();
    }
}
