package by.belstu.it.lyskov.jms;

import by.belstu.it.lyskov.bean.Item;

import javax.jms.*;
import javax.naming.*;

public class QueueMessageProducer {

    public void send(Item item) {
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
                JMSProducer producer = context.createProducer();
                ObjectMessage itemMessage = context.createObjectMessage(item);
                producer.send(lombardQueue, itemMessage);
                System.out.println("Message was placed to the queue");

                System.out.println("-------------------------------");
                JMSConsumer consumer = context.createConsumer(lombardQueue);
                ObjectMessage message = (ObjectMessage) consumer.receive();
                System.out.println("Received message contains:\n" + message.getObject());
                System.out.println("Message info:" + message);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to initialize connection factory");
    }

    public static void main(String[] args) {
        Item item = new Item();
        item.setName("Test lombard item");
        item.setPrice(100);
        new QueueMessageProducer().send(item);
    }
}
