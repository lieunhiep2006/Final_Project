package com.bakershop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bakershop.dao.FavoriteDAO;
import com.bakershop.model.User;

@WebServlet("/favorites")
public class FavoriteController extends HttpServlet {
    private final FavoriteDAO favoriteDAO = new FavoriteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            request.setAttribute("cakes", favoriteDAO.getCakesForUser(user.getId()));
        } catch (java.sql.SQLException exception) {
            getServletContext().log("Unable to load favorites for user " + user.getId(), exception);
            throw new ServletException("Unable to load favorite cakes", exception);
        }
        request.setAttribute("favoritesPage", true);
        request.setAttribute("returnTo", request.getContextPath() + "/favorites");
        request.getRequestDispatcher("/WEB-INF/views/shop/product-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        long cakeId;
        try {
            cakeId = Long.parseLong(request.getParameter("cakeId"));
            if (cakeId <= 0) {
                throw new NumberFormatException("Cake ID must be positive");
            }
        } catch (NumberFormatException | NullPointerException exception) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid cake ID");
            return;
        }

        try {
            favoriteDAO.toggle(user.getId(), cakeId);
        } catch (IllegalStateException exception) {
            getServletContext().log("Unable to update favorite for user " + user.getId()
                    + " and cake " + cakeId, exception);
            throw new ServletException("Unable to update favorite", exception);
        }

        String returnTo = request.getParameter("returnTo");
        String productsPath = request.getContextPath() + "/products";
        String favoritesPath = request.getContextPath() + "/favorites";
        if (returnTo == null || !(returnTo.equals(productsPath)
                || returnTo.startsWith(productsPath + "?") || returnTo.equals(favoritesPath))) {
            returnTo = productsPath;
        }
        response.sendRedirect(response.encodeRedirectURL(returnTo));
    }
}
