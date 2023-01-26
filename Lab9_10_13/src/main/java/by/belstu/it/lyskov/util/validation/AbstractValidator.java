package by.belstu.it.lyskov.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractValidator {

    public boolean isFieldValid(String pattern, String field) {
        Pattern patternCompile = Pattern.compile(pattern);
        Matcher matcher = patternCompile.matcher(field);
        return matcher.matches();
    }
}
