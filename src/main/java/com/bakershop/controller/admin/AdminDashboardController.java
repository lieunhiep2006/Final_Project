package com.bakershop.controller.admin;

import com.bakershop.dao.CakeDAO;
import com.bakershop.dao.OrderDAO;
import com.bakershop.model.Cake;
import com.bakershop.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {

    private CakeDAO cakeDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() {
        cakeDAO = new CakeDAO();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Cake> cakes = cakeDAO.getAllCakes();
        List<Order> orders = orderDAO.getAllOrders();

        int totalCakes = cakes.size();

        int totalStock = 0;

        for (Cake cake : cakes) {
            totalStock += cake.getStockQuantity();
        }

        int pendingOrders = orderDAO.countPendingOrders();

        int lowStock = cakeDAO.countLowStockCakes();

        int outOfStock = cakeDAO.countOutOfStockCakes();

        double totalRevenue = orderDAO.getTotalRevenue();

        double paidRevenue = orderDAO.getPaidRevenue();

        int completedOrders = orderDAO.countCompletedOrders();

        double completedRevenue =
                orderDAO.getRevenueByStatus("Completed");

        double deliveringRevenue =
                orderDAO.getRevenueByStatus("Delivering");

        double pendingRevenue =
                orderDAO.getRevenueByStatus("Pending");

        double cancelledRevenue =
                orderDAO.getRevenueByStatus("Cancelled");

        request.setAttribute("cakes", cakes);
        request.setAttribute("orders", orders);

        request.setAttribute("totalCakes", totalCakes);
        request.setAttribute("totalStock", totalStock);
        request.setAttribute("pendingOrders", pendingOrders);
        request.setAttribute("lowStock", lowStock);
        request.setAttribute("outOfStock", outOfStock);

        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("paidRevenue", paidRevenue);

        request.setAttribute("completedOrders", completedOrders);
        request.setAttribute("completedRevenue", completedRevenue);
        request.setAttribute("deliveringRevenue", deliveringRevenue);
        request.setAttribute("pendingRevenue", pendingRevenue);
        request.setAttribute("cancelledRevenue", cancelledRevenue);

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/dashboard.jsp"
        ).forward(request, response);
    }
}