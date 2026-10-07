package com.bakershop.controller;

import com.bakershop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/mock-login")
public class MockLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        User adminUser = new User();
        adminUser.setId(1L);
        adminUser.setFullName("Quản Trị Viên (Admin)");
        adminUser.setPhoneNumber("0999999999");
        adminUser.setPasswordHash("123");
        adminUser.setRole("ADMIN");

        HttpSession session = request.getSession();
        session.setAttribute("user", adminUser);
        session.setAttribute("account", adminUser);
        response.sendRedirect(request.getContextPath() + "/admin/dashboard"); // Hoặc đường dẫn mapping dashboard của bạn
    }
}