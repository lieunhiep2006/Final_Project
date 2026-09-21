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
@WebServlet("/home")
public class HomeController extends HttpServlet{
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cake> top8Cakes;
        CakeDAO cakeDAO = new CakeDAO();
        
        top8Cakes = cakeDAO.getTop8Cakes();
        request.setAttribute("top8Cakes", top8Cakes);
        request.getRequestDispatcher("/WEB-INF/views/shop/home.jsp").forward(request, response);
    }
}
