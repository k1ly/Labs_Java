package by.belstu.it.lyskov.jms;

import by.belstu.it.lyskov.bean.Item;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

public class QueueMessageConsumer implements MessageListener {

    public void receive() {
        ConnectionFactory connectionFactory = null;
        Queue lombardQueue = null;
        try {
            InitialContext initialContext = new InitialContext();
            connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
            lombardQueue = (Queue) initialContext.lookup("jms/PTPQueue");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if (connectionFactory != null) {
            try (JMSContext context = connectionFactory.createContext()) {
                JMSConsumer consumer = context.createConsumer(lombardQueue);
                consumer.setMessageListener(this);
                System.out.println("Listening for the message from queue ...");
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to initialize connection factory");
    }

    @Override
    public void onMessage(Message message) {
        try {
            Item item = message.getBody(Item.class);
            System.out.println("Received message contains:\n" + item);
            System.out.println("Message info:" + message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QueueMessageConsumer messageConsumer = new QueueMessageConsumer();
        messageConsumer.receive();
    }
}
