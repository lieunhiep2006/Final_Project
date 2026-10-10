package com.bakershop.controller;

import com.bakershop.dao.CartDAO;
import com.bakershop.model.Cart;
import com.bakershop.model.CartItem;
import com.bakershop.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    private CartDAO cartDAO;

    @Override
    public void init() {
        cartDAO = new CartDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = cartDAO.getOrCreateCartByUserId(user.getId());
        double total = 0.0;
        for (CartItem item : cart.getItems()) {
            total += item.getLineTotal();
        }

        request.setAttribute("cartItems", cart.getItems());
        request.setAttribute("cartTotal", total);


        request.getRequestDispatcher("/WEB-INF/views/shop/cart.jsp").forward(request, response);
    }

    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = cartDAO.getOrCreateCartByUserId(user.getId());
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                long cakeId = Long.parseLong(request.getParameter("cakeId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String size = request.getParameter("size");
                String note = request.getParameter("note");
                
                if (size == null || size.trim().isEmpty()) size = "M";
                cartDAO.addOrUpdateItem(cart.getId(), cakeId, size, quantity, note);

            } else if ("update".equals(action)) {
                long itemId = Long.parseLong(request.getParameter("itemId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String note = request.getParameter("note");
                String size = request.getParameter("size");

                cartDAO.updateCartItem(itemId, quantity, note, size);
            } else if ("remove".equals(action)) {
                long itemId = Long.parseLong(request.getParameter("itemId"));
                cartDAO.removeItem(itemId);

            } else if ("clear".equals(action)) {
                cartDAO.clearCart(cart.getId());
            }
        } catch (Exception e) {
            session.setAttribute("cartError", "Lỗi cartController");
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}