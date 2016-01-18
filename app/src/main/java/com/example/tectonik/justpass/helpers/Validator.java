package com.example.tectonik.justpass.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final String EMAIL_REGEX = "[A-z0-9]+@[A-z0-9]+\\.[A-z]+";
    private final String USERNAME_REGEX = "^(?=.*A-z)(?=.*0-9)$";
    private final String PASSWORD_REGEX = "^(?=.*A-z)(?=.*0-9)$";

    public boolean validatePassword(String password) {
        if (password != null) {
            Pattern validator = Pattern.compile(PASSWORD_REGEX);
            Matcher matcher = validator.matcher(password);

            return matcher.matches();
        }

        return false;
    }

    public boolean validateUsername(String username) {
        if (username != null) {
            Pattern validator = Pattern.compile(USERNAME_REGEX);
            Matcher matcher = validator.matcher(username);

            return matcher.matches();
        }

        return false;
    }

    public boolean validateEmail(String email) {
        if (email != null) {
            Pattern validator = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = validator.matcher(email);

            return matcher.matches();
        }

        return false;
    }
}
