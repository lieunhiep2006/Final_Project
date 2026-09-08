package com.bakershop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bakershop.dao.UserDAO;
import com.bakershop.model.User;

@WebServlet(name = "AuthController", urlPatterns = {"/login", "/register", "/logout"})
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/login");
        } else if ("/register".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if ("/register".equals(request.getServletPath())) {
            handleRegister(request, response);
        } else if ("/login".equals(request.getServletPath())) {
            handleLogin(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        if (userDAO.checkPhoneExists(phone)) {
            request.setAttribute("error", "Số điện thoại này đã được đăng ký!");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(fullName, phone, password, address, "CUSTOMER");
        if (userDAO.register(newUser)) {
            request.setAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại!");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phone = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        User user = userDAO.login(phone, password);

        if (user == null) {
            request.setAttribute("error", "Sai số điện thoại hoặc mật khẩu!");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        request.getSession(true).setAttribute("account", user);
        if ("ADMIN".equals(user.getRole())) {
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
