package by.belstu.it.lyskov.command.impl;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.bean.dto.UserDTO;
import by.belstu.it.lyskov.command.Command;
import by.belstu.it.lyskov.command.CommandResult;
import by.belstu.it.lyskov.command.exception.CommandException;
import by.belstu.it.lyskov.controller.page.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignOutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.WARNING_PAGE.getPath());
        HttpSession session = request.getSession();
        UserDTO user = new UserDTO();
        user.setRoleName(UserRole.GUEST);
        session.setAttribute("user", user);
        return commandResult;
    }
}
