package com.bakershop.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakershop.dao.CakeDAO;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CakeDAO dao = new CakeDAO();
        request.setAttribute("cakeList", dao.getAllCakesWithThumbnail());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/shop/home.jsp");
        dispatcher.forward(request,response);
    }
}