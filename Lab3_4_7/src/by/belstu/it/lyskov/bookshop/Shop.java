package by.belstu.it.lyskov.bookshop;

import by.belstu.it.lyskov.exception.ServiceException;

public interface Shop<T> {
    boolean add(T product) throws ServiceException;
    boolean update(int id, T product) throws ServiceException;
    T find(int id) throws ServiceException;
    T sell(int id) throws ServiceException;
}