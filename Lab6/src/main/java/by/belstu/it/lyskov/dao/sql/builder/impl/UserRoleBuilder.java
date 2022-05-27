package by.belstu.it.lyskov.dao.sql.builder.impl;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.dao.sql.builder.Builder;
import by.belstu.it.lyskov.dao.sql.dbtable.UserRoleTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleBuilder implements Builder<UserRole> {

    @Override
    public UserRole build(ResultSet resultSet) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getInt(UserRoleTable.ID.getColumnName()));
        userRole.setName(resultSet.getString(UserRoleTable.ROLE_NAME.getColumnName()));
        return userRole;
    }
}
