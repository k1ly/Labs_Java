package by.belstu.it.lyskov.service.impl;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.dao.DAOProvider;
import by.belstu.it.lyskov.dao.UserRoleDAO;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import by.belstu.it.lyskov.dao.sql.dbtable.UserRoleTable;
import by.belstu.it.lyskov.service.UserRoleService;
import by.belstu.it.lyskov.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public boolean addUserRole(UserRole userRole) throws ServiceException {
        boolean isUserRoleAdded;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserRoleDAO userRoleDAO = daoProvider.getUserRoleDAO();
        try {
            isUserRoleAdded = userRoleDAO.addUserRole(userRole);
        } catch (DAOException e) {
            throw new ServiceException("User role adding error", e);
        }
        return isUserRoleAdded;
    }

    @Override
    public Optional<UserRole> findUserRoleById(int userRoleId) throws ServiceException {
        Optional<UserRole> userRole;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserRoleDAO userRoleDAO = daoProvider.getUserRoleDAO();
        try {
            Map<String, Object> userRoleIdParameter = new LinkedHashMap<>();
            userRoleIdParameter.put(UserRoleTable.ID.getColumnName(), userRoleId);
            List<UserRole> userRoleList = userRoleDAO.findUserRoles(ParameterMap.of(userRoleIdParameter));
            userRole = userRoleList.size() == 1 ? Optional.of(userRoleList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User role searching error", e);
        }
        return userRole;
    }

    @Override
    public Optional<UserRole> findUserRoleByName(String userRoleName) throws ServiceException {
        Optional<UserRole> userRole;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserRoleDAO userRoleDAO = daoProvider.getUserRoleDAO();
        try {
            Map<String, Object> userRoleNameParameter = new LinkedHashMap<>();
            userRoleNameParameter.put(UserRoleTable.ROLE_NAME.getColumnName(), userRoleName);
            List<UserRole> userRoleList = userRoleDAO.findUserRoles(ParameterMap.of(userRoleNameParameter));
            userRole = userRoleList.size() == 1 ? Optional.of(userRoleList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User role searching error", e);
        }
        return userRole;
    }
}
