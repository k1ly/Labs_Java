package by.belstu.it.lyskov.dao.sql.builder.impl;

import by.belstu.it.lyskov.bean.Item;
import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.dao.sql.builder.Builder;
import by.belstu.it.lyskov.dao.sql.dbtable.ItemTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemBuilder implements Builder<Item> {

    @Override
    public Item build(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt(ItemTable.ID.getColumnName()));
        item.setName(resultSet.getString(ItemTable.NAME.getColumnName()));
        item.setPrice(resultSet.getInt(ItemTable.PRICE.getColumnName()));
        item.setOwner(new User(resultSet.getInt(ItemTable.OWNER_ID.getColumnName())));
        return item;
    }
}
