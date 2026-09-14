package com.bakershop.controller;

import com.bakershop.dao.UserDAO;
import com.bakershop.model.User;
import com.bakershop.utils.PasswordUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login", "/register", "/logout"})
public class AuthController extends HttpServlet {
	private final UserDAO userDAO = new UserDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if ("/logout".equals(path)) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/auth" + path + ".jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if ("/login".equals(request.getServletPath())) {
			login(request, response);
		} else {
			register(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = userDAO.findByPhoneNumber(request.getParameter("phoneNumber"));
		if (user == null || !PasswordUtils.matches(request.getParameter("password"), user.getPasswordHash())) {
			request.setAttribute("error", "Số điện thoại hoặc mật khẩu không đúng.");
			request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
			return;
		}
		user.setPasswordHash(null);
		request.getSession(true).setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/home");
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String fullName = request.getParameter("fullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String address = request.getParameter("address");
		if (isBlank(fullName) || isBlank(phoneNumber) || isBlank(password) || !password.equals(confirmPassword)) {
			request.setAttribute("error", "Vui lòng điền đầy đủ thông tin và xác nhận mật khẩu chính xác.");
			request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
			return;
		}
		phoneNumber = phoneNumber.trim();
		if (userDAO.findByPhoneNumber(phoneNumber) != null) {
			request.setAttribute("error", "Số điện thoại này đã được đăng ký.");
			request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
			return;
		}
		User user = new User();
		user.setFullName(fullName.trim());
		user.setPhoneNumber(phoneNumber);
		user.setPasswordHash(PasswordUtils.hash(password));
		user.setAddress(address == null ? "" : address.trim());
		user.setRole("CUSTOMER");
		if (!userDAO.create(user)) {
			request.setAttribute("error", "Không thể tạo tài khoản. Vui lòng thử lại.");
			request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/login?registered=true");
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
