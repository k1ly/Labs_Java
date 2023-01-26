package by.belstu.it.lyskov.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicMessageProducer {

    public void send(String text) {
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
                JMSProducer producer = context.createProducer();
                TextMessage textMessage = context.createTextMessage(text);
                producer.setTimeToLive(3000).send(lombardTopic, textMessage);
                System.out.println("Message was placed to the topic");
            }
        } else
            System.out.println("Failed to initialize connection factory");
    }

    public static void main(String[] args) {
        new TopicMessageProducer().send("Very important message!");
    }
}
