package by.belstu.it.lyskov.controller.filter;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.bean.dto.UserDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SessionFilter implements Filter {
    private static final int MAX_INACTIVE_INTERVAL = 60 * 10;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
        if (session.getAttribute("user") == null) {
                User user = new User();
                user.setRole(new UserRole(UserRole.GUEST));
                session.setAttribute("user", UserDTO.convert(user));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
