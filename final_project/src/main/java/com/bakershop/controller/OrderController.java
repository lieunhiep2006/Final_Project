package com.bakershop.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.util.List;


import com.bakershop.dao.OrderDAO;

import com.bakershop.model.Order;
import com.bakershop.model.User;
@WebServlet("/orders")
public class OrderController extends HttpServlet {
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null) {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders;
            orders = orderDAO.getListOrderById(user.getId());
            request.setAttribute("orders", orders);
        }
        request.getRequestDispatcher("/WEB-INF/views/shop/order-history.jsp").forward(request, response);
    }
}
