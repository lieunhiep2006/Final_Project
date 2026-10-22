<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BakerShop - Đổi mật khẩu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
</head>
<body>
<aside class="sidebar">
    <div class="brand">
        <div class="brand-icon">🍰</div>
        <div>
            <div class="brand-name">BakerShop</div>
            <div class="brand-subtitle">Admin Panel</div>
        </div>
    </div>
    <nav class="sidebar-menu">
        <a href="<c:url value='/admin/dashboard'/>">
            <span>🏠</span> Dashboard
        </a>
        <a href="<c:url value='/admin/manage-cakes'/>">
            <span>🍰</span> Manage Cakes
        </a>
        <a href="<c:url value='/admin/manage-orders'/>">
            <span>🛒</span> Manage Orders
        </a>
        <a href="<c:url value='/admin/reviews'/>">
            <span>⭐</span> Manage Reviews
        </a>
    </nav>
</aside>

<main class="main-content">
    <div class="page-header">
        <div>
            <h1>🔑 Đổi mật khẩu</h1>
            <p>Cập nhật mật khẩu bảo mật cho tài khoản quản trị.</p>
        </div>
    </div>

    <section class="form-card" style="max-width: 600px;">
        <div class="section-title">
            <span class="title-icon">🔒</span>
            <h2>Thay đổi mật khẩu</h2>
        </div>

        <c:if test="${not empty message}">
            <p style="color: #27ae60; font-weight: bold; margin-bottom: 15px;">${message}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/change-password" method="POST" class="cake-form">
            <div class="form-group">
                <label>Mật khẩu cũ</label>
                <input type="password" name="oldPassword" placeholder="Nhập mật khẩu hiện tại" required>
            </div>
            <div class="form-group">
                <label>Mật khẩu mới</label>
                <input type="password" name="newPassword" placeholder="Nhập mật khẩu mới" required>
            </div>
            <div class="form-submit">
                <button type="submit">🔑 Cập nhật mật khẩu</button>
            </div>
        </form>
    </section>
</main>
</body>
</html>