package by.belstu.it.lyskov.controller.filter;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.bean.dto.UserDTO;
import by.belstu.it.lyskov.controller.page.Page;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RegistrationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user.getRoleName().equals(UserRole.GUEST)) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(Page.REGISTER_PAGE.getPath());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
