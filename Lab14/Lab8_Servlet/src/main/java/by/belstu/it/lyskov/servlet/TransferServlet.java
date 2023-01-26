package by.belstu.it.lyskov.servlet;

import java.io.*;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Transfer", value = "/transfer")
public class TransferServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<html><body>Transfer declined!</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int value = Integer.parseInt(request.getParameter("value"));
        if (value > 1000) {
            ConnectionFactory connectionFactory;
            Queue lombardQueue;
            try {
                InitialContext initialContext = new InitialContext();
                connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
                lombardQueue = (Queue) initialContext.lookup("jms/WebQueue");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
            if (connectionFactory != null) {
                try (JMSContext context = connectionFactory.createContext()) {
                    JMSProducer producer = context.createProducer();
                    TextMessage message = context.createTextMessage("Transfer value: " + value);
                    producer.send(lombardQueue, message);
                    System.out.println("Transfer value is over 1000");
                }
            }
        }
        response.sendRedirect("/lab8/confirm.jsp");
    }
}