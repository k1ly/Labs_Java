package by.belstu.it.lyskov.command.impl;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.bean.dto.UserDTO;
import by.belstu.it.lyskov.command.Command;
import by.belstu.it.lyskov.command.CommandResult;
import by.belstu.it.lyskov.command.exception.CommandException;
import by.belstu.it.lyskov.service.ServiceProvider;
import by.belstu.it.lyskov.service.UserService;
import by.belstu.it.lyskov.service.exception.ServiceException;
import by.belstu.it.lyskov.controller.page.Page;
import by.belstu.it.lyskov.util.controllerutil.CookieHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class SignInCommand implements Command {
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.WARNING_PAGE.getPath());
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password.getBytes());
            Optional<User> loggedInUser = userService.signIn(user);
            if (loggedInUser.isPresent()) {
                if (loggedInUser.get().getLogin() != null) {
                    UserDTO userDTO = UserDTO.convert(loggedInUser.get());
                    session.setAttribute("user", userDTO);
                    commandResult.setPath(Page.WELCOME_PAGE.getPath());
                    CookieHandler cookieHandler = CookieHandler.getInstance();
                    Map<String, Object> userInfo = new LinkedHashMap<>();
                    userInfo.put("user", userDTO);
                    userInfo.put("lastEntrance", LocalTime.now());
                    Cookie userCookie = cookieHandler.createCookie("userInfo", userInfo);
                    userCookie.setMaxAge(COOKIE_MAX_AGE);
                    response.addCookie(userCookie);
                }
            } else
                session.setAttribute("errorMessage", "Неверный логин или пароль");
        } catch (ServiceException e) {
            throw new CommandException("Error during authentication", e);
        }
        return commandResult;
    }
}