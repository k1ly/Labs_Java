package by.belstu.it.lyskov.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "servlet1", value = "/servlet1")
public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isRedirect = request.getParameter("isRedirect");
        request.setAttribute("info", "Redirecting from servlet 1 (GET)");
        if (isRedirect == null) {
            ServletContext servletContext = request.getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/servlet2");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/servlet2");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isRedirect = request.getParameter("isRedirect");
        request.setAttribute("info", "Redirecting from servlet 1 (POST)");
        if (isRedirect == null) {
            ServletContext servletContext = request.getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/servlet2");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/servlet2");
        }
    }


}
