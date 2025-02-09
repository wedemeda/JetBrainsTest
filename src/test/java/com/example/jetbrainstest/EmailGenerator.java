package com.example.jetbrainstest;

import java.util.stream.Stream;

public class EmailGenerator {

    public static Stream<String> randomEmails() {
        return Stream.of(generateRandomEmail());
    }

    public static String generateRandomEmail() {
        String domain = "test.com";
        String randomString = "user" + (int) (Math.random() * 9000);
        return randomString + "@" + domain;
    }
}