package by.belstu.it.lyskov.command.impl;

import by.belstu.it.lyskov.bean.Item;
import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.bean.dto.UserDTO;
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

public class AddItemCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.ADD_ITEM.getPath());
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ItemService itemService = serviceProvider.getItemService();
        try {
            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            Object userAttr = session.getAttribute("user");
            if (userAttr != null) {
                UserDTO user = (UserDTO) userAttr;
                item.setOwner(new User(user.getId()));
            }
            boolean addStatus = itemService.addItem(item);
            if (addStatus)
                commandResult.setPath(Page.getContextPath() + "/lombard");
            else session.setAttribute("errorMessage", "Произошла ошибка. Предмет не был добавлен");
        } catch (ServiceException e) {
            throw new CommandException("Error during adding the item", e);
        }
        return commandResult;
    }
}