package com.bakershop.controller.admin;

import com.bakershop.model.User;
import com.bakershop.dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/profile", "/admin/change-password", "/admin/settings"})
public class AdminProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/admin/profile":
                request.getRequestDispatcher("/WEB-INF/views/admin/profile.jsp").forward(request, response);
                break;
            case "/admin/change-password":
                request.getRequestDispatcher("/WEB-INF/views/admin/change-password.jsp").forward(request, response);
                break;
            case "/admin/settings":
                request.getRequestDispatcher("/WEB-INF/views/admin/settings.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/admin/change-password".equals(path)) {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");

            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            if (oldPassword == null || newPassword == null || confirmPassword == null ||
                oldPassword.trim().isEmpty() || newPassword.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
                request.setAttribute("message", "❌ Vui lòng điền đầy đủ thông tin!");
                request.getRequestDispatcher("/WEB-INF/views/admin/change-password.jsp").forward(request, response);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("message", "❌ Mật khẩu mới và xác nhận mật khẩu không khớp!");
                request.getRequestDispatcher("/WEB-INF/views/admin/change-password.jsp").forward(request, response);
                return;
            }

            UserDAO userDAO = new UserDAO();
            User latestUser = userDAO.getUserByPhoneNumber(currentUser.getPhoneNumber());

            if (latestUser == null || !com.bakershop.utils.PasswordUtils.checkPassword(oldPassword, latestUser.getPasswordHash())) {
                request.setAttribute("message", "❌ Mật khẩu cũ không chính xác!");
                request.getRequestDispatcher("/WEB-INF/views/admin/change-password.jsp").forward(request, response);
                return;
            }

            boolean updated = userDAO.updatePasswordByPhone(currentUser.getPhoneNumber(), newPassword);

            if (updated) {
                latestUser.setPasswordHash(userDAO.getUserByPhoneNumber(currentUser.getPhoneNumber()).getPasswordHash());
                session.setAttribute("user", latestUser);
                request.setAttribute("message", "✅ Đổi mật khẩu thành công!");
            } else {
                request.setAttribute("message", "❌ Lỗi khi cập nhật cơ sở dữ liệu!");
            }

            request.getRequestDispatcher("/WEB-INF/views/admin/change-password.jsp").forward(request, response);
        }
    }
}