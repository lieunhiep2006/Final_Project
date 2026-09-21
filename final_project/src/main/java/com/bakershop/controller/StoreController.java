package com.bakershop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import com.bakershop.model.Store;
import com.bakershop.dao.StoreDAO;

@WebServlet("/store")
public class StoreController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Store> stores;
        StoreDAO storeDAO = new StoreDAO();

        stores = storeDAO.getStores();
        request.setAttribute("stores", stores);
        request.getRequestDispatcher("/WEB-INF/views/shop/store-list.jsp").forward(request, response);
    }
}
