package com.bakershop.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bakershop.dao.StoreDAO;

@WebServlet("/stores")
public class StoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StoreDAO dao = new StoreDAO();
        request.setAttribute("storeList", dao.getAllStores());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/shop/store-list.jsp");
        dispatcher.forward(request, response);
    }
}