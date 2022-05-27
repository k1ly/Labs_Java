package by.belstu.it.lyskov.dao;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;

import java.util.List;

public interface UserDAO {

    boolean addUser(User user) throws DAOException;

    List<User> findUsers(ParameterMap parameters) throws DAOException;
}
