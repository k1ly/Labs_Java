package by.belstu.it.lyskov.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

public class TopicMessageConsumer implements MessageListener {

    public void receive() {
        javax.jms.ConnectionFactory connectionFactory = null;
        Topic lombardTopic = null;
        try {
            InitialContext initialContext = new InitialContext();
            connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
            lombardTopic = (Topic) initialContext.lookup("jms/PubSubTopic");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if (connectionFactory != null) {
            try (JMSContext context = connectionFactory.createContext()) {
                JMSConsumer consumer = context.createConsumer(lombardTopic);
                consumer.setMessageListener(this);
                System.out.println("Listening for the message from topic ...");
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
            String text = message.getBody(String.class);
            System.out.println("Received message:\n" + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> new TopicMessageConsumer().receive()).start();
        new Thread(() -> new TopicMessageConsumer().receive()).start();
    }
}
