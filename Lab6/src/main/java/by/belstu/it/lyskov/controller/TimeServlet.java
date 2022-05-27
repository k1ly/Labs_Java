package by.belstu.it.lyskov.controller;

import java.io.*;
import java.time.LocalTime;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "TimeServlet", value = "/time")
public class TimeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        LocalTime time = LocalTime.now();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Текущее время:</h3>");
        out.println("<h1>" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + "</h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<td>Протокол</td>");
        out.println("<td>" + request.getProtocol() + "</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Адрес клиента</td>");
        out.println("<td>" + request.getRemoteAddr() + "</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Имя клиента</td>");
        out.println("<td>" + request.getLocalName() + "</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Метод</td>");
        out.println("<td>" + request.getMethod() + "</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>URL</td>");
        out.println("<td>" + request.getRequestURL() + "</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<h2>Заголовки запроса</h2>");
        var headerNames = request.getHeaderNames();
        out.println("<table>");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("<tr>");
            out.println("<td>" + headerName + "</td>");
            out.println("<td>" + request.getHeader(headerName) + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }
}