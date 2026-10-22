package com.bakershop.controller.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakershop.dao.ReviewDAO;
import com.bakershop.model.Review;

@WebServlet("/admin/reviews")
public class AdminReviewServlet extends HttpServlet {
    private ReviewDAO reviewDAO;

    @Override
    public void init() throws ServletException {
        this.reviewDAO = new ReviewDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String star = request.getParameter("star");
        List<Review> reviewList = reviewDAO.getAllReviews(star);

        request.setAttribute("reviewList", reviewList);
        request.setAttribute("selectedStar", star);

        request.getRequestDispatcher("/WEB-INF/views/admin/reviews.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            reviewDAO.deleteReview(id);
        }
        response.sendRedirect(request.getContextPath() + "/admin/reviews");
    }
}