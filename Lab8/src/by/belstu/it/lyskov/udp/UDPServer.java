package by.belstu.it.lyskov.udp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(7000);
            System.out.println("Waiting for datagrams...");
            while (true) {
                var buffer = new byte[1024];
                DatagramPacket input = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(input);
                String string = new String(buffer).replaceAll("\0", "");
                System.out.println(input.getAddress() + ":" + input.getPort() + " (message received) -> " + string);

                String response = "Response to message: " + string;
                DatagramPacket output = new DatagramPacket(response.getBytes(), response.getBytes().length, input.getAddress(), input.getPort());
                datagramSocket.send(output);
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
