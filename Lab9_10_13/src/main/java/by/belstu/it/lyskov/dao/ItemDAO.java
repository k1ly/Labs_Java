package by.belstu.it.lyskov.dao;

import by.belstu.it.lyskov.bean.Item;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;

import java.util.List;

public interface ItemDAO {

    boolean addItem(Item item) throws DAOException;

    List<Item> findItems(ParameterMap parameters) throws DAOException;

    List<Item> browseItems() throws DAOException;

    boolean deleteItem(ParameterMap deleteId) throws DAOException;
}
