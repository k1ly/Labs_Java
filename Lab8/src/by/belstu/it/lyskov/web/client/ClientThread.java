package by.belstu.it.lyskov.web.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread extends Thread {
    int id;
    PrintStream output;
    BufferedReader input;
    InetAddress address;

    public ClientThread(Socket socket, int id) throws IOException {
        this.id = id;
        this.output = new PrintStream(socket.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.address = socket.getInetAddress();
    }

    @Override
    public void run() {
        String line;
        try {
            output.println("Client-" + id);
            while ((line = input.readLine()) != null) {
                System.out.println("[Client " + id + "]: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Disconnect -> ", e);
        } finally {
            disconnect();
        }
    }

    public void disconnect() {
        try {
            if (input != null)
                input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.interrupt();
        }
    }
}
