package by.belstu.it.lyskov.service;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    boolean addUserRole(UserRole userRole) throws ServiceException;

    Optional<UserRole> findUserRoleById(int userRoleId) throws ServiceException;

    Optional<UserRole> findUserRoleByName(String userRoleName) throws ServiceException;
}
