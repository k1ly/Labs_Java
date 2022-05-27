package by.belstu.it.lyskov.web.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 5; i++) {
                Socket socket = new Socket(InetAddress.getLocalHost(), 3000);
                ClientThread clientThread = new ClientThread(socket, i + 1);
                clientThread.start();
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException | IOException e) {
            logger.error(e);
        }
    }
}
