package by.belstu.it.lyskov.command;

import by.belstu.it.lyskov.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public final class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();

    private static CommandProvider instance;

    private final Map<String, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put("register_user", new RegisterUserCommand());
        repository.put("sign_in", new SignInCommand());
        repository.put("sign_out", new SignOutCommand());
        repository.put("add_item", new AddItemCommand());
        repository.put("delete_item", new DeleteItemCommand());
        repository.put("get_item_list", new GetItemList());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    if (instance == null)
                        instance = new CommandProvider();
                    else {
                        logger.warn("CommandProvider instance is been already initializing by another thread");
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

    public Command getCommand(String name) {
        Command command;
        try {
            command = repository.get(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = null;
        }
        return command;
    }
}
