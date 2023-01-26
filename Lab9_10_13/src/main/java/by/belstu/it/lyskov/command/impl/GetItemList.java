package by.belstu.it.lyskov.command.impl;

import by.belstu.it.lyskov.bean.Item;
import by.belstu.it.lyskov.command.Command;
import by.belstu.it.lyskov.command.CommandResult;
import by.belstu.it.lyskov.command.exception.CommandException;
import by.belstu.it.lyskov.controller.page.Page;
import by.belstu.it.lyskov.service.ItemService;
import by.belstu.it.lyskov.service.ServiceProvider;
import by.belstu.it.lyskov.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class GetItemList implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.ITEM_LIST.getPage());

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ItemService itemService = serviceProvider.getItemService();
        try {
            List<Item> itemList = itemService.browseItems();
            request.setAttribute("itemList", itemList);
        } catch (ServiceException e) {
            throw new CommandException("Error during loading Item list page", e);
        }
        return commandResult;
    }
}