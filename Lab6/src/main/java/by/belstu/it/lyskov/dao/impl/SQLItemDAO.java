package by.belstu.it.lyskov.dao.impl;

import by.belstu.it.lyskov.bean.Item;
import by.belstu.it.lyskov.dao.ItemDAO;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import by.belstu.it.lyskov.dao.sql.AbstractSQLDAO;
import by.belstu.it.lyskov.dao.sql.dbtable.ItemTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLItemDAO extends AbstractSQLDAO<Item> implements ItemDAO {
    private static final String TABLE_NAME = "items";

    public SQLItemDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addItem(Item item) throws DAOException {
        boolean isItemAdded = false;
        if (item != null) {
            try {
                Integer generatedId = insertEntity(takeFields(item));
                item.setId(generatedId);
                isItemAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("Item inserting error", exception);
            }
        }
        return isItemAdded;
    }

    @Override
    public List<Item> findItems(ParameterMap parameters) throws DAOException {
        List<Item> itemList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters());
            for (Object item : executeQuery(Item.class, sql)) {
                itemList.add((Item) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Item selecting error", exception);
        }
        return itemList;
    }

    @Override
    public List<Item> browseItems() throws DAOException {
        List<Item> itemList = new ArrayList<>();
        try {
            String sql = makeQuery();
            for (Object item : executeQuery(Item.class, sql)) {
                itemList.add((Item) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Item selecting error", exception);
        }
        return itemList;
    }

    @Override
    public boolean deleteItem(ParameterMap deleteId) throws DAOException {
        boolean isItemDeleted;
        try {
            isItemDeleted = deleteEntity(deleteId) == 1;
        } catch (SQLException exception) {
            throw new DAOException("Item deleting error", exception);
        }
        return isItemDeleted;
    }

    @Override
    public ParameterMap takeFields(Item item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (item.getName() != null)
            fields.put(ItemTable.NAME.getColumnName(), item.getName());
        if (item.getPrice() != null)
            fields.put(ItemTable.PRICE.getColumnName(), item.getPrice());
        if (item.getOwner() != null && item.getOwner().getId() > 0)
            fields.put(ItemTable.OWNER_ID.getColumnName(), item.getOwner().getId());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return ItemTable.ID.getColumnName()
                + ", " + ItemTable.NAME.getColumnName()
                + ", " + ItemTable.PRICE.getColumnName()
                + ", " + ItemTable.OWNER_ID.getColumnName();
    }
}
