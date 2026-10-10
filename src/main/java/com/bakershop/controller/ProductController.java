package com.bakershop.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.bakershop.dao.CakeDAO;
import com.bakershop.dao.CategoryDAO;
import com.bakershop.model.Cake;
import com.bakershop.model.Category;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        String catId = request.getParameter("categoryId");

        CakeDAO cakeDAO = new CakeDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        Category cat = new Category();
        List<Cake> cakes;

        if(query != null && !query.isEmpty()) {
            cakes = cakeDAO.getCakeBySearch(query);
        }
        else if(catId != null && !catId.isEmpty()) {
            cakes = cakeDAO.getCakeByCategory(catId);
            cat = categoryDAO.getCategoryById(catId);
        }
        else {
            cakes = cakeDAO.getAllCakes();
        }

        request.setAttribute("cakes", cakes);
        request.setAttribute("category", cat);

        request.getRequestDispatcher("/WEB-INF/views/shop/product-list.jsp").forward(request, response);
    }
}