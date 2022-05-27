package by.belstu.it.lyskov.udp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            DatagramSocket datagram = new DatagramSocket();
            for (int i = 0; i < 10; i++) {
                String message = "Line №" + i;
                DatagramPacket output = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getLocalHost(), 7000);
                datagram.send(output);

                var buffer = new byte[1024];
                DatagramPacket input = new DatagramPacket(buffer, buffer.length);
                datagram.receive(input);
                var data = input.getData();
                String string = new String(data).replaceAll("\0", "");
                System.out.println("Server " + input.getAddress().getHostAddress() + ":" + input.getPort() + " received message. (" + string + ")");
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
