package by.belstu.it.lyskov.service.impl;

import by.belstu.it.lyskov.bean.Item;
import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.dao.DAOProvider;
import by.belstu.it.lyskov.dao.ItemDAO;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import by.belstu.it.lyskov.dao.sql.dbtable.ItemTable;
import by.belstu.it.lyskov.service.ItemService;
import by.belstu.it.lyskov.service.ServiceProvider;
import by.belstu.it.lyskov.service.UserService;
import by.belstu.it.lyskov.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    @Override
    public boolean addItem(Item item) throws ServiceException {
        boolean status;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ItemDAO itemDAO = daoProvider.getItemDAO();
        try {
            status = itemDAO.addItem(item);
        } catch (DAOException e) {
            throw new ServiceException("Item adding error", e);
        }
        return status;
    }

    @Override
    public List<Item> browseItems() throws ServiceException {
        List<Item> itemList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ItemDAO itemDAO = daoProvider.getItemDAO();
        try {
            itemList = itemDAO.browseItems();
            if (itemList.size() > 0) {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                UserService userService = serviceProvider.getUserService();
                for (Item item : itemList) {
                    Optional<User> user = userService.findUserById(item.getOwner().getId());
                    user.ifPresent(item::setOwner);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Obtaining items error", e);
        }
        return itemList;
    }

    @Override
    public Optional<Item> findItemById(int itemId) throws ServiceException {
        Optional<Item> item;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ItemDAO itemDAO = daoProvider.getItemDAO();
        try {
            Map<String, Object> itemIdParameter = new LinkedHashMap<>();
            itemIdParameter.put(ItemTable.ID.getColumnName(), itemId);
            List<Item> itemList = itemDAO.findItems(ParameterMap.of(itemIdParameter));
            item = itemList.size() == 1 ? Optional.of(itemList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Item searching error", e);
        }
        return item;
    }

    @Override
    public boolean deleteItem(int itemId) throws ServiceException {
        boolean isItemDeleted;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ItemDAO itemDAO = daoProvider.getItemDAO();
        try {
            Map<String, Object> itemIdParameter = new LinkedHashMap<>();
            itemIdParameter.put(ItemTable.ID.getColumnName(), itemId);
            isItemDeleted = itemDAO.deleteItem(ParameterMap.of(itemIdParameter));
        } catch (DAOException e) {
            throw new ServiceException("Item deleting error", e);
        }
        return isItemDeleted;
    }
}
