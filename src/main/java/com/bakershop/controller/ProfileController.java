package com.bakershop.controller;

import com.bakershop.dao.UserDAO;
import com.bakershop.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private final UserDAO userDAO = new UserDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!isAuthenticated(request, response)) {
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!isAuthenticated(request, response)) {
			return;
		}

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		if (fullName == null || fullName.trim().isEmpty()) {
			request.setAttribute("error", "Vui lòng nhập họ và tên.");
			request.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp").forward(request, response);
			return;
		}

		user.setFullName(fullName.trim());
		user.setAddress(address == null ? "" : address.trim());
		if (!userDAO.updateProfile(user)) {
			request.setAttribute("error", "Không thể cập nhật thông tin tài khoản.");
			request.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp").forward(request, response);
			return;
		}

		request.setAttribute("success", "Thông tin tài khoản đã được cập nhật.");
		request.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp").forward(request, response);
	}

	private boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;
	}
}