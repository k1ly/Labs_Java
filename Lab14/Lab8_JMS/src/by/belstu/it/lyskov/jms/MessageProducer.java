package by.belstu.it.lyskov.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageProducer {

    public void send() {
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
            try (JMSContext context = connectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE)) {
                JMSProducer producer = context.createProducer();

                producer.setPriority(0).send(lombardTopic, "Message 1");
                producer.setPriority(6).setProperty("flag", "TRUE").send(lombardTopic, "Message 2");
                producer.setPriority(2).setProperty("flag", "FALSE").send(lombardTopic, "Message 3");
                producer.clearProperties();
                producer.setPriority(9).send(lombardTopic, "Message 4");

                System.out.println("Messages were placed to the topic");
            }
        } else
            System.out.println("Failed to initialize connection factory");
    }

    public static void main(String[] args) {
         new MessageProducer().send();
    }
}
