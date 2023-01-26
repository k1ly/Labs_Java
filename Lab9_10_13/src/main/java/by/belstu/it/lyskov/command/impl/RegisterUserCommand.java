package by.belstu.it.lyskov.command.impl;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.bean.dto.UserDTO;
import by.belstu.it.lyskov.command.Command;
import by.belstu.it.lyskov.command.CommandResult;
import by.belstu.it.lyskov.command.exception.CommandException;
import by.belstu.it.lyskov.service.ServiceProvider;
import by.belstu.it.lyskov.service.UserService;
import by.belstu.it.lyskov.service.exception.ServiceException;
import by.belstu.it.lyskov.controller.page.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;

public class RegisterUserCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.REGISTER_PAGE.getPath());
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        byte[] password = request.getParameter("password").getBytes();
        byte[] repeatPassword = request.getParameter("repeatPassword").getBytes();
        String name = request.getParameter("name");

        if (Arrays.equals(password, repeatPassword)) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserService userService = serviceProvider.getUserService();
            try {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setName(name);
                user.setRole(new UserRole(UserRole.CLIENT));

                if (userService.findUserByLogin(user.getLogin()).isEmpty()) {
                    int registerStatus = userService.registerUser(user);
                    if (registerStatus >= 0) {
                        if (registerStatus > 0) {
                            UserDTO userDTO = UserDTO.convert(user);
                            session.setAttribute("user", userDTO);
                            commandResult.setPath(Page.WELCOME_PAGE.getPath());
                        } else
                            session.setAttribute("errorMessage", "Ошибка, не удалось зарегистрировать пользователя");
                    } else
                        session.setAttribute("errorMessage", "Неверные входные данные, попробуйте снова");
                } else
                    session.setAttribute("errorMessage", "Такой пользователь уже существует");
            } catch (ServiceException e) {
                throw new CommandException("Error during registration", e);
            }
        }
        return commandResult;
    }
}