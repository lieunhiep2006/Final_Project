package com.bakershop.filter;

import com.bakershop.dao.CartDAO;
import com.bakershop.dao.CategoryDAO;
import com.bakershop.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class AppLayoutFilter implements Filter {

    private CategoryDAO categoryDAO;
    private CartDAO cartDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        categoryDAO = new CategoryDAO();
        cartDAO = new CartDAO();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);


        req.setAttribute("categoryList", categoryDAO.categoryList());


        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int cartCount = cartDAO.getCartCount(user.getId());
            session.setAttribute("cartCount", cartCount);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}