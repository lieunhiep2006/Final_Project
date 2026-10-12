package com.bakershop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakershop.dao.CakeDAO;
import com.bakershop.model.Cake;

@WebServlet("/product")
public class ProductDetailController extends HttpServlet {
    private final CakeDAO cakeDAO = new CakeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long cakeId;
        try {
            cakeId = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException | NullPointerException exception) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Cake cake = cakeDAO.getCakeById(cakeId);
        if (cake == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("cake", cake);
        request.setAttribute("minimumDeliveryDate", java.time.LocalDate.now());
        request.getRequestDispatcher("/WEB-INF/views/shop/product-detail.jsp").forward(request, response);
    }
}
