package by.belstu.it.lyskov.service.impl;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.dao.DAOProvider;
import by.belstu.it.lyskov.dao.UserDAO;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import by.belstu.it.lyskov.dao.sql.dbtable.UserTable;
import by.belstu.it.lyskov.service.ServiceProvider;
import by.belstu.it.lyskov.service.UserRoleService;
import by.belstu.it.lyskov.service.UserService;
import by.belstu.it.lyskov.service.exception.ServiceException;
import by.belstu.it.lyskov.util.hash.SHA256PasswordHash;
import by.belstu.it.lyskov.util.validation.UserValidator;

import java.util.*;

public class UserServiceImpl implements UserService {

    @Override
    public int registerUser(User user) throws ServiceException {
        int status = -1;
        UserValidator userValidator = UserValidator.getInstance();
        if (userValidator.isUserValid(user)) {
            status = 0;
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserRoleService userRoleService = serviceProvider.getUserRoleService();
            Optional<UserRole> userRole = userRoleService.findUserRoleByName(user.getRole().getName());
            if (userRole.isPresent()) {
                user.setRole(userRole.get());
                DAOProvider daoProvider = DAOProvider.getInstance();
                UserDAO userDAO = daoProvider.getUserDAO();
                try {
                    SHA256PasswordHash sha256PasswordHash = SHA256PasswordHash.getInstance();
                    byte[] passwordHash = sha256PasswordHash.computeHash(user.getPassword());
                    user.setPassword(passwordHash);
                    status = userDAO.addUser(user) ? 1 : 0;
                } catch (DAOException e) {
                    throw new ServiceException("Registration error", e);
                }
            }
        }
        return status;
    }

    @Override
    public Optional<User> signIn(User user) throws ServiceException {
        Optional<User> loggedInUser = Optional.empty();
        UserValidator userValidator = UserValidator.getInstance();
        if (userValidator.isLoginValid(user.getLogin()) && userValidator.isPasswordValid(user.getPassword())) {
            loggedInUser = findUserByLogin(user.getLogin());
            if (loggedInUser.isPresent()) {
                SHA256PasswordHash sha256PasswordHash = SHA256PasswordHash.getInstance();
                byte[] passwordHash = sha256PasswordHash.computeHash(user.getPassword());
                if (Arrays.equals(passwordHash, loggedInUser.get().getPassword())) {
                    ServiceProvider serviceProvider = ServiceProvider.getInstance();
                    UserRoleService userRoleService = serviceProvider.getUserRoleService();
                    Optional<UserRole> userRole = userRoleService.findUserRoleById(loggedInUser.get().getRole().getId());
                    if (userRole.isPresent())
                        loggedInUser.get().setRole(userRole.get());
                } else loggedInUser = Optional.of(new User());
            }
        }
        return loggedInUser;
    }

    @Override
    public Optional<User> findUserById(int userId) throws ServiceException {
        Optional<User> user;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        try {
            Map<String, Object> userIdParameter = new LinkedHashMap<>();
            userIdParameter.put(UserTable.ID.getColumnName(), userId);
            List<User> userList = userDAO.findUsers(ParameterMap.of(userIdParameter));
            user = userList.size() == 1 ? Optional.of(userList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User searching error", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByLogin(String userLogin) throws ServiceException {
        Optional<User> user;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        try {
            Map<String, Object> userLoginParameter = new LinkedHashMap<>();
            userLoginParameter.put(UserTable.LOGIN.getColumnName(), userLogin);
            List<User> userList = userDAO.findUsers(ParameterMap.of(userLoginParameter));
            user = userList.size() == 1 ? Optional.of(userList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User searching error", e);
        }
        return user;
    }
}