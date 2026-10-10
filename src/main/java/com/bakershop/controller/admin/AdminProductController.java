package com.bakershop.controller.admin;

import com.bakershop.dao.CakeDAO;
import com.bakershop.model.Cake;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-cakes")
public class AdminProductController extends HttpServlet {

    private CakeDAO cakeDAO;    

    @Override
    public void init() {
        cakeDAO = new CakeDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            cakeDAO.deleteCake(id);
            response.sendRedirect(request.getContextPath() + "/admin/manage-cakes");
            return;
        }

        if ("edit".equals(action)) {
            long id = Long.parseLong(request.getParameter("id"));
            Cake cake = cakeDAO.getCakeById(id);
            request.setAttribute("editCake", cake);
        }

        List<Cake> cakes;
        if (keyword != null && !keyword.trim().isEmpty()) {
            cakes = cakeDAO.searchCakes(keyword.trim());
        } else {
            cakes = cakeDAO.getAllCakes();
        }

        request.setAttribute("keyword", keyword);
        request.setAttribute("cakes", cakes);

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/manage-cakes.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            Cake cake = new Cake();
            cake.setName(request.getParameter("name"));
            cake.setDescription(request.getParameter("description"));
            cake.setPrice(Double.parseDouble(request.getParameter("price")));
            cake.setStockQuantity(Integer.parseInt(request.getParameter("stockQuantity")));
            cake.setCategoryId(Long.parseLong(request.getParameter("categoryId")));

            cakeDAO.insertCake(cake);
        }

        if ("update".equals(action)) {
            Cake cake = new Cake();
            cake.setId(Long.parseLong(request.getParameter("id")));
            cake.setName(request.getParameter("name"));
            cake.setDescription(request.getParameter("description"));
            cake.setPrice(Double.parseDouble(request.getParameter("price")));
            cake.setStockQuantity(Integer.parseInt(request.getParameter("stockQuantity")));
            cake.setCategoryId(Long.parseLong(request.getParameter("categoryId")));

            cakeDAO.updateCake(cake);
        }

        response.sendRedirect(request.getContextPath() + "/admin/manage-cakes");
    }
}