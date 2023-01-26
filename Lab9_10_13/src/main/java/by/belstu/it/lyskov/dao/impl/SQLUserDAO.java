package by.belstu.it.lyskov.dao.impl;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.dao.UserDAO;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import by.belstu.it.lyskov.dao.sql.AbstractSQLDAO;
import by.belstu.it.lyskov.dao.sql.dbtable.UserTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLUserDAO extends AbstractSQLDAO<User> implements UserDAO {
    private static final String TABLE_NAME = "users";

    public SQLUserDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addUser(User user) throws DAOException {
        boolean isUserAdded = false;
        if (user != null) {
            try {
                Integer generatedId = insertEntity(takeFields(user));
                user.setId(generatedId);
                isUserAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("User inserting error", exception);
            }
        }
        return isUserAdded;
    }

    @Override
    public List<User> findUsers(ParameterMap parameters) throws DAOException {
        List<User> userList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters());
            for (Object item : executeQuery(User.class, sql)) {
                userList.add((User) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("User selecting error", exception);
        }
        return userList;
    }

    @Override
    public ParameterMap takeFields(User user) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (user.getLogin() != null)
            fields.put(UserTable.LOGIN.getColumnName(), user.getLogin());
        if (user.getPassword() != null)
            fields.put(UserTable.PASSWORD.getColumnName(), user.getPassword());
        if (user.getName() != null)
            fields.put(UserTable.NAME.getColumnName(), user.getName());
        if (user.getRole() != null && user.getRole().getId() > 0)
            fields.put(UserTable.USER_ROLE_ID.getColumnName(), user.getRole().getId());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return UserTable.ID.getColumnName()
                + ", " + UserTable.LOGIN.getColumnName()
                + ", " + UserTable.PASSWORD.getColumnName()
                + ", " + UserTable.NAME.getColumnName()
                + ", " + UserTable.USER_ROLE_ID.getColumnName();
    }
}
