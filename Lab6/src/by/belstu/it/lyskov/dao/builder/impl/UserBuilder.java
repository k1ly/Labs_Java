package by.belstu.it.lyskov.dao.builder.impl;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.dao.builder.Builder;
import by.belstu.it.lyskov.dao.dbtable.UserTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(UserTable.ID.getColumnName()));
        user.setName(resultSet.getString(UserTable.NAME.getColumnName()));
        user.setBirthDate(resultSet.getTimestamp(UserTable.BIRTH_DATE.getColumnName()));
        return user;
    }
}
