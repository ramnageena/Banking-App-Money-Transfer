package com.banking_app.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Custom email validator implementing ConstraintValidator
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    // Email regex pattern (adjust it based on your needs)
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return false; // Or true, based on whether you allow null/empty emails
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}