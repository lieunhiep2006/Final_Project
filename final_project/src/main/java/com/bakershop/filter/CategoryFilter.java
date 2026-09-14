package com.bakershop.filter;

import com.bakershop.dao.CategoryDAO;
import com.bakershop.model.Category;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter("/*") 
public class CategoryFilter implements Filter {

    private CategoryDAO categoryDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        categoryDAO = new CategoryDAO();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        

        if (req.getAttribute("categoryList") == null) {
            List<Category> categoryList = categoryDAO.categoryList();
            req.setAttribute("categoryList", categoryList);
        }


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}