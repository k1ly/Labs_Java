package by.belstu.it.lyskov.service;

import by.belstu.it.lyskov.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();

    private static ServiceProvider instance;

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final ItemService itemService;

    private ServiceProvider() {
        userService = new UserServiceImpl();
        userRoleService = new UserRoleServiceImpl();
        itemService = new ItemServiceImpl();
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    if (instance == null)
                        instance = new ServiceProvider();
                    else {
                        logger.warn("ServiceFactory instance is been already initializing by another thread");
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

    public UserService getUserService() {
        return userService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public ItemService getItemService() {
        return itemService;
    }
}
