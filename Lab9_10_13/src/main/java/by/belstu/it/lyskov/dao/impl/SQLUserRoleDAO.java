package by.belstu.it.lyskov.dao.impl;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.dao.UserRoleDAO;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import by.belstu.it.lyskov.dao.sql.AbstractSQLDAO;
import by.belstu.it.lyskov.dao.sql.dbtable.UserRoleTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLUserRoleDAO extends AbstractSQLDAO<UserRole> implements UserRoleDAO {
    private static final String TABLE_NAME = "user_roles";

    public SQLUserRoleDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addUserRole(UserRole userRole) throws DAOException {
        boolean isUserRoleAdded = false;
        if (userRole != null) {
            try {
                Integer generatedId = insertEntity(takeFields(userRole));
                userRole.setId(generatedId);
                isUserRoleAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("User role inserting error", exception);
            }
        }
        return isUserRoleAdded;
    }

    @Override
    public List<UserRole> findUserRoles(ParameterMap parameters) throws DAOException {
        List<UserRole> userRoleList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters());
            for (Object item : executeQuery(UserRole.class, sql)) {
                userRoleList.add((UserRole) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("User role selecting error", exception);
        }
        return userRoleList;
    }

    @Override
    public ParameterMap takeFields(UserRole userRole) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (userRole.getName() != null)
            fields.put(UserRoleTable.ROLE_NAME.getColumnName(), userRole.getName());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return UserRoleTable.ID.getColumnName()
                + ", " + UserRoleTable.ROLE_NAME.getColumnName();
    }
}
