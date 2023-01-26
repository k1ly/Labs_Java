package by.belstu.it.lyskov.dao;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;

import java.util.List;

public interface UserRoleDAO {

    boolean addUserRole(UserRole userRole) throws DAOException;

    List<UserRole> findUserRoles(ParameterMap parameters) throws DAOException;
}
