package by.belstu.it.lyskov.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

public class MessageConsumer implements MessageListener {

    public void receive() {
        ConnectionFactory connectionFactory = null;
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
                context.setClientID("JMSClient" + this.hashCode());
                JMSConsumer consumer = context.createConsumer(lombardTopic, "flag <> 'TRUE'");
//                JMSConsumer consumer = context.createDurableConsumer(lombardTopic, "durableConsumer");
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
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> new MessageConsumer().receive()).start();
    }
}
