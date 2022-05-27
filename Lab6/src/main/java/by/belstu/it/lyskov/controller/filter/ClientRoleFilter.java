package by.belstu.it.lyskov.controller.filter;

import by.belstu.it.lyskov.bean.UserRole;
import by.belstu.it.lyskov.bean.dto.UserDTO;
import by.belstu.it.lyskov.controller.page.Page;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ClientRoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object userAttr = session.getAttribute("user");
        if (userAttr == null || UserRole.GUEST.equals(((UserDTO) userAttr).getRoleName())) {
            response.sendRedirect(Page.WARNING_PAGE.getPath());
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }
}
