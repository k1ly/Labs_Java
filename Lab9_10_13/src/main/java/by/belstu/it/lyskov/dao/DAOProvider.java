package by.belstu.it.lyskov.dao;

import by.belstu.it.lyskov.dao.impl.SQLItemDAO;
import by.belstu.it.lyskov.dao.impl.SQLUserDAO;
import by.belstu.it.lyskov.dao.impl.SQLUserRoleDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DAOProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();

    private static DAOProvider instance;

    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    private ItemDAO itemDAO;

    private DAOProvider() {
        userDAO = new SQLUserDAO();
        userRoleDAO = new SQLUserRoleDAO();
        itemDAO = new SQLItemDAO();
    }

    public static DAOProvider getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    if (instance == null)
                        instance = new DAOProvider();
                    else {
                        logger.warn("DAOProvider instance is been already initializing by another thread");
                    }
                } else {
                    logger.error("Timeout exceeded");
                    throw new RuntimeException("Timeout exceeded");
                }
            } catch (InterruptedException exception) {
                logger.error(exception);
                throw new RuntimeException(exception.getMessage(), exception);
            } finally {
                if (lock.isHeldByCurrentThread())
                    lock.unlock();
            }
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public synchronized void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserRoleDAO getUserRoleDAO() {
        return userRoleDAO;
    }

    public synchronized void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public synchronized void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
}
