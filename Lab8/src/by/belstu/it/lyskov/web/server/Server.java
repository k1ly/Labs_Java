package by.belstu.it.lyskov.web.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            List<ServerThread> clients = new ArrayList<>();
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Waiting for request...");
            while (true) {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getHostName() + " connected");
                ServerThread thread = new ServerThread(socket, clients);
                clients.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            logger.error("Server error: ", e);
        }
    }
}
