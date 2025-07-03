package com.auction.utils;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9_]{3,20}$";

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidUsername(String username) {
        return username != null && Pattern.matches(USERNAME_REGEX, username);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
