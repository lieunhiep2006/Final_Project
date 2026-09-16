package com.bakershop.controller;

import com.bakershop.dao.CakeDAO;
import com.bakershop.dao.ReviewDAO;
import com.bakershop.model.Cake;
import com.bakershop.model.Review;
import com.bakershop.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/reviews")
public class ReviewController extends HttpServlet {
	private final CakeDAO cakeDAO = new CakeDAO();
	private final ReviewDAO reviewDAO = new ReviewDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cakeId = parseCakeId(request.getParameter("cakeId"));
		Cake cake = cakeId > 0 ? cakeDAO.findById(cakeId) : null;
		if (cake == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("cake", cake);
		request.setAttribute("reviews", reviewDAO.findByCakeId(cakeId));
		request.getRequestDispatcher("/WEB-INF/views/shop/reviews.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cakeId = parseCakeId(request.getParameter("cakeId"));
		HttpSession session = request.getSession(false);
		User user = session == null ? null : (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int rating = parseRating(request.getParameter("rating"));
		String comment = request.getParameter("comment");
		if (cakeId <= 0 || rating < 1 || rating > 5 || comment == null || comment.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/reviews?cakeId=" + cakeId + "&error=invalid");
			return;
		}
		Review review = new Review();
		review.setUserId(user.getId());
		review.setCakeId(cakeId);
		review.setRating(rating);
		review.setComment(comment.trim());
		reviewDAO.create(review);
		response.sendRedirect(request.getContextPath() + "/reviews?cakeId=" + cakeId + "&success=true");
	}

	private int parseCakeId(String value) {
		try { return Integer.parseInt(value); } catch (NumberFormatException | NullPointerException exception) { return -1; }
	}

	private int parseRating(String value) {
		try { return Integer.parseInt(value); } catch (NumberFormatException | NullPointerException exception) { return 0; }
	}
}