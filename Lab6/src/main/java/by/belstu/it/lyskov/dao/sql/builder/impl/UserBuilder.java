package by.belstu.it.lyskov.dao.sql.builder.impl;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.dao.sql.builder.Builder;
import by.belstu.it.lyskov.dao.sql.dbtable.UserTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(UserTable.ID.getColumnName()));
        user.setLogin(resultSet.getString(UserTable.LOGIN.getColumnName()));
        user.setPassword(resultSet.getBytes(UserTable.PASSWORD.getColumnName()));
        user.setName(resultSet.getString(UserTable.NAME.getColumnName()));
        user.setRole(new UserRole(resultSet.getInt(UserTable.USER_ROLE_ID.getColumnName())));
        return user;
    }
}
