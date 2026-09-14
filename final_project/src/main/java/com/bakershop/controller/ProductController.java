package com.bakershop.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import com.bakershop.dao.CakeDAO;
import com.bakershop.model.Cake;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String catId = request.getParameter("categoryId");

        CakeDAO cakeDAO = new CakeDAO();

        List<Cake> cakes;
        if(catId != null && !catId.isEmpty()) {
            cakes = cakeDAO.getCakeByCategory(catId);
        }
        else {
            cakes = cakeDAO.getAllCakes();
        }

        request.setAttribute("cakes", cakes);

        request.getRequestDispatcher("/WEB-INF/views/shop/product-list.jsp").forward(request, response);
    }
}