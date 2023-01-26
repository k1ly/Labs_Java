package by.belstu.it.lyskov.jms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

public class WebLoggerConsumer implements MessageListener {
    private static final Logger logger = LogManager.getLogger();

    public void receive() {
        ConnectionFactory connectionFactory = null;
        Queue lombardQueue = null;
        try {
            InitialContext initialContext = new InitialContext();
            connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
            lombardQueue = (Queue) initialContext.lookup("jms/WebQueue");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if (connectionFactory != null) {
            try (JMSContext context = connectionFactory.createContext()) {
                JMSConsumer consumer = context.createConsumer(lombardQueue);
                consumer.setMessageListener(this);
                System.out.println("Listening for message from the queue to log ...");
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            String text = message.getBody(String.class);
            logger.warn(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebLoggerConsumer messageConsumer = new WebLoggerConsumer();
        messageConsumer.receive();
    }
}
