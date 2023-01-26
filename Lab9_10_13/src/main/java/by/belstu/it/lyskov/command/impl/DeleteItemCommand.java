package by.belstu.it.lyskov.command.impl;

import by.belstu.it.lyskov.command.Command;
import by.belstu.it.lyskov.command.CommandResult;
import by.belstu.it.lyskov.command.exception.CommandException;
import by.belstu.it.lyskov.controller.page.Page;
import by.belstu.it.lyskov.service.ItemService;
import by.belstu.it.lyskov.service.ServiceProvider;
import by.belstu.it.lyskov.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteItemCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.getContextPath() + "/lombard");
        HttpSession session = request.getSession();

        int itemId = Integer.parseInt(request.getParameter("item_id"));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ItemService itemService = serviceProvider.getItemService();
        try {
            if (!itemService.deleteItem(itemId))
                session.setAttribute("errorMessage", "Произошла ошибка. Предмет не был удален");
        } catch (ServiceException e) {
            throw new CommandException("Error during deleting the item", e);
        }
        return commandResult;
    }
}