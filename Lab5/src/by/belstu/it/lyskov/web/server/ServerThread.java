package by.belstu.it.lyskov.web.server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread {
    List<ServerThread> clients;
    PrintStream output;
    BufferedReader input;
    InetAddress address;

    public ServerThread(Socket socket, List<ServerThread> clients) throws IOException {
        this.clients = clients;
        this.output = new PrintStream(socket.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.address = socket.getInetAddress();
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = input.readLine()) != null) {
                sendBroadcast(line + " entered chat.");
                System.out.println(line + " entered chat.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Disconnect -> ", e);
        } finally {
            disconnect();
        }
    }

    public void disconnect() {
        try {
            if (output != null)
                output.close();
            if (input != null)
                input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.interrupt();
        }
    }

    private void sendBroadcast(String message) {
        for (ServerThread c : clients) {
            c.output.println(message);
        }
    }
}