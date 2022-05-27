package by.belstu.it.lyskov.util.validation;

import by.belstu.it.lyskov.bean.User;

public class UserValidator extends AbstractValidator {
    private final static String LOGIN_PATTERN = "^[A-Za-z]\\w{5,20}$";
    private final static String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private final static String NAME_PATTERN = "^(\\p{L})+([(. )'-](\\p{L})+)*$";
    private static final UserValidator instance = new UserValidator();

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        return instance;
    }

    public boolean isLoginValid(String login) {
        return login != null && isFieldValid(LOGIN_PATTERN, login);
    }

    public boolean isPasswordValid(byte[] password) {
        return password != null && isFieldValid(PASSWORD_PATTERN, new String(password));
    }

    public boolean isNameValid(String name) {
        return name != null && isFieldValid(NAME_PATTERN, name);
    }

    public boolean isUserValid(User user) {
        if (user == null)
            return false;
        boolean isUserValid = true;
        if (user.getLogin() != null)
            isUserValid = isLoginValid(user.getLogin());
        if (user.getPassword() != null)
            isUserValid &= isPasswordValid(user.getPassword());
        if (user.getName() != null)
            isUserValid &= isNameValid(user.getName());
        return isUserValid;
    }
}
