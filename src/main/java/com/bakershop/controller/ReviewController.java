package com.bakershop.controller;

import com.bakershop.dao.CakeDAO;
import com.bakershop.dao.ReviewDAO;
import com.bakershop.model.Cake;
import com.bakershop.model.Review;
import com.bakershop.model.User;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/reviews")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 5 * 1024 * 1024)
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

		String imageUrl = saveUploadedImage(request);
		Review review = new Review();
		review.setUserId(user.getId());
		review.setCakeId((long) cakeId);
		review.setRating((double) rating);
		review.setComment(comment.trim());
		review.setImageUrl(imageUrl);
		reviewDAO.create(review);
		response.sendRedirect(request.getContextPath() + "/reviews?cakeId=" + cakeId + "&success=true");
	}

	private String saveUploadedImage(HttpServletRequest request) throws IOException, ServletException {
		Part filePart = request.getPart("reviewImage");
		if (filePart == null || filePart.getSize() == 0) {
			return null;
		}

		String submittedFileName = filePart.getSubmittedFileName();
		if (submittedFileName == null || submittedFileName.trim().isEmpty()) {
			return null;
		}

		String fileExtension = "";
		int dotIndex = submittedFileName.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < submittedFileName.length() - 1) {
			fileExtension = submittedFileName.substring(dotIndex);
		}
		String safeExtension = fileExtension.toLowerCase();
		if (!safeExtension.equals(".jpg") && !safeExtension.equals(".jpeg") && !safeExtension.equals(".png") && !safeExtension.equals(".webp")) {
			return null;
		}

		String uploadDir = getServletContext().getRealPath("/statics/images/reviews");
		if (uploadDir == null) {
			uploadDir = new File("src/main/webapp/statics/images/reviews").getAbsolutePath();
		}
		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		String fileName = UUID.randomUUID() + safeExtension;
		String targetPath = new File(directory, fileName).getAbsolutePath();
		filePart.write(targetPath);
		return "reviews/" + fileName;
	}

	private int parseCakeId(String value) {
		try { return Integer.parseInt(value); } catch (NumberFormatException | NullPointerException exception) { return -1; }
	}

	private int parseRating(String value) {
		try { return Integer.parseInt(value); } catch (NumberFormatException | NullPointerException exception) { return 0; }
	}
}