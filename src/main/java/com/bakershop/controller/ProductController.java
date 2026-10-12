package com.bakershop.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.bakershop.dao.CakeDAO;
import com.bakershop.dao.CategoryDAO;
import com.bakershop.dao.FavoriteDAO;
import com.bakershop.model.Cake;
import com.bakershop.model.Category;
import com.bakershop.model.User;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        String catId = request.getParameter("categoryId");

        CakeDAO cakeDAO = new CakeDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        Category cat = new Category();
        List<Cake> cakes;

        if(query != null && !query.isEmpty()) {
            cakes = cakeDAO.getCakeBySearch(query);
        }
        else if(catId != null && !catId.isEmpty()) {
            cakes = cakeDAO.getCakeByCategory(catId);
            cat = categoryDAO.getCategoryById(catId);
        }
        else {
            cakes = cakeDAO.getAllCakes();
        }

        request.setAttribute("cakes", cakes);
        request.setAttribute("category", cat);
        String queryString = request.getQueryString();
        request.setAttribute("returnTo", request.getRequestURI()
                + (queryString == null || queryString.isEmpty() ? "" : "?" + queryString));

        javax.servlet.http.HttpSession session = request.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");
        if (user != null) {
            try {
                java.util.Set<Long> favoriteCakeIds = new FavoriteDAO().getCakeIdsForUser(user.getId());
                for (Cake cake : cakes) {
                    cake.setFavorited(favoriteCakeIds.contains(cake.getId()));
                }
            } catch (java.sql.SQLException exception) {
                throw new ServletException("Unable to load favorite products", exception);
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/shop/product-list.jsp").forward(request, response);
    }
}