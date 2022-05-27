package by.belstu.it.lyskov.service;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    int registerUser(User user) throws ServiceException;

    Optional<User> signIn(User user) throws ServiceException;

    Optional<User> findUserById(int userId) throws ServiceException;

    Optional<User> findUserByLogin(String userLogin) throws ServiceException;

}
