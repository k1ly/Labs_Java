package by.belstu.it.lyskov.main;

import by.belstu.it.lyskov.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Organization organization = new Organization(3);
        try {
            for (int i = 0; i < 7; i++) {
                new Client(organization, "Client#" + (i + 1)).start();
            }
            TimeUnit.SECONDS.sleep(30);
            Parking parking = new Parking(5);
            for (int i = 0; i < 15; i++) {
                new Car(parking, i).start();
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }
}
