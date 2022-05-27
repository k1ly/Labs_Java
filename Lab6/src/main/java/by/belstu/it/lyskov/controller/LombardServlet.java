package by.belstu.it.lyskov.controller;

import by.belstu.it.lyskov.command.Command;
import by.belstu.it.lyskov.command.CommandProvider;
import by.belstu.it.lyskov.command.CommandResult;
import by.belstu.it.lyskov.command.exception.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "Lombard", value = "/lombard")
public class LombardServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private static final String DEFAULT_COMMAND = "get_item_list";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandResult commandResult = processRequest(request, response);
        String page = commandResult.getPath();
        ServletContext servletContext = request.getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommandResult commandResult = processRequest(request, response);
        String page = commandResult.getPath();
        response.sendRedirect(page);
    }

    private CommandResult processRequest(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult;
        try {
            CommandProvider commandProvider = CommandProvider.getInstance();
            String commandName = request.getParameter("command");
            Command executionCommand = commandProvider.getCommand(commandName != null ? commandName : DEFAULT_COMMAND);
            commandResult = executionCommand.execute(request, response);
        } catch (CommandException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return commandResult;
    }
}
