package by.belstu.it.lyskov.controller;

import by.belstu.it.lyskov.command.Command;
import by.belstu.it.lyskov.command.CommandProvider;
import by.belstu.it.lyskov.command.CommandResult;
import by.belstu.it.lyskov.command.exception.CommandException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "Auth", value = "/auth")
public class AuthServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "";
        ServletContext servletContext = request.getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter("command");
        CommandProvider provider = CommandProvider.getInstance();
        Command executionCommand = provider.getCommand(commandName);
        CommandResult commandResult;
        try {
            commandResult = executionCommand.execute(request, response);
        } catch (CommandException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        String page = commandResult.getPath();
        response.sendRedirect(page);
    }
}
