package by.belstu.it.lyskov.servlet;

import java.io.*;
import java.util.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Result", value = "/result")
public class ResultServlet extends HttpServlet {
    private static final String PAGE = "/result.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> list = new ArrayList<>();
        list.add("Ivanov");
        list.add("Petrov");
        list.add("Sidorov");
        list.add("Patsei");
        request.setAttribute("list", list);

        Random random = new Random();
        request.setAttribute("number", random.nextInt(3) - 1);

        Date date = new Date();
        request.setAttribute("date", date);
        request.setAttribute("price", 123);

        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }
}