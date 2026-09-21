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


@WebServlet({"/login", "/register", "/logout", "/profile"})
public class AuthController extends HttpServlet {
    private UserDAO userDAO;
    @Override 
    public void init() throws ServletException {
        this.userDAO = new UserDAO();
    }
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = request.getServletPath();

        switch (url) {
            case "/login":
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                break;
            case "/register":
                request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
                break;
            case "/logout":
                handleLogout(request, response);
                break;
            case "/profile":
                request.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String url = request.getServletPath();
        switch (url) {
            case "/login":
                handleLogin(request, response);
                break;
            case "/register":
                handleRegister(request, response);
                break;
            case "/profile":
                handleUpdateProfile(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }

    private void handleUpdateProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            UserDAO userDAO = new UserDAO();
            userDAO.updateUser(user.getId(), fullName, phoneNumber, address);
            user.setFullName(fullName);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            session.setAttribute("user", user);
            
        }
        response.sendRedirect(request.getContextPath() + "/home");
    }
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        if(phoneNumber == null || password == null || phoneNumber.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        User user = userDAO.login(phoneNumber.trim(), password.trim());

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            
            response.sendRedirect(request.getContextPath() + "/home");
            

        } else {
            request.setAttribute("error", "Sai số điện thoại hoặc mật khẩu!");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }
    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if(fullName == null || fullName.trim().isEmpty() || phoneNumber == null || phoneNumber.trim().isEmpty() || address == null || address.trim().isEmpty() || password == null || password.trim().isEmpty() || confirmPassword == null || confirmPassword.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }
        if(userDAO.getUserByPhoneNumber(phoneNumber.trim()) != null) {
            request.setAttribute("error", "Tài khoản đã tồn tại");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }
        if(!password.trim().equals(confirmPassword.trim())) {
            request.setAttribute("error", "Chưa xác nhận được mật khẩu");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }
        User newUser = new User(
            null,
            fullName.trim(),
            phoneNumber.trim(),
            password.trim(),
            address.trim(),
            "Customer"
        );

        boolean isSuccess = userDAO.register(newUser);
        if(isSuccess) {
            HttpSession session = request.getSession();
            session.setAttribute("authSuccess", "Đăng ký thành công! Vui lòng đăng nhập.");
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else {
            request.setAttribute("error", "Đăng ký thất bại! Đã có lỗi xảy ra phía máy chủ.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        }
        


        
    }
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }

}
    


