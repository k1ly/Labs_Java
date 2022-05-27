package by.belstu.it.lyskov.service;

import by.belstu.it.lyskov.bean.Item;
import by.belstu.it.lyskov.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    boolean addItem(Item item) throws ServiceException;

    List<Item> browseItems() throws ServiceException;

    Optional<Item> findItemById(int itemId) throws ServiceException;

    boolean deleteItem(int itemId) throws ServiceException;
}
