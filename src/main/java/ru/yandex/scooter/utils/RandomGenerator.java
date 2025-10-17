package ru.yandex.scooter.utils;

import java.util.Random;

public class RandomGenerator {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    public static String generateRandomLogin() {
        return "test_courier_" + generateRandomString(8);
    }

    public static String generateRandomPassword() {
        return "password_" + generateRandomString(6);
    }

    public static String generateRandomFirstName() {
        return "FirstName_" + generateRandomString(6);
    }

}
