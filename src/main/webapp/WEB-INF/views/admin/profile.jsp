<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BakerShop - Thông tin tài khoản Admin</title>
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
            <a class="active" href="<c:url value='/admin/dashboard'/>">
                <span>🏠</span>
                Dashboard
            </a>
            <a href="<c:url value='/admin/manage-cakes'/>">
                <span>🍰</span>
                Manage Cakes
            </a>
            <a href="<c:url value='/admin/manage-orders'/>">
                <span>🛒</span>
                Manage Orders
            </a>
            <a href="<c:url value='/admin/manage-vouchers'/>">
                <span>🎟️</span>
                Manage Vouchers
            </a>
            <a href="<c:url value='/admin/reviews'/>">
                <span>⭐</span>
                Manage Reviews
            </a>
    </nav>
</aside>

<main class="main-content">
    <div class="page-header">
        <div>
            <h1>👤 Thông tin tài khoản Admin</h1>
            <p>Chi tiết hồ sơ cá nhân của quản trị viên.</p>
        </div>
    </div>

    <section class="dashboard-card" style="max-width: 600px;">
        <div class="dashboard-card-header">
            <div class="section-title">
                <span class="title-icon">👤</span>
                <h2>Thông tin cá nhân</h2>
            </div>
        </div>
        <div class="table-wrapper" style="padding: 10px 20px 20px 20px;">
            <table class="dashboard-table">
                <tbody>
                    <tr>
                        <th style="width: 35%;">Họ và tên</th>
                        <td><strong>${sessionScope.user.fullName}</strong></td>
                    </tr>
                    <tr>
                        <th>Số điện thoại</th>
                        <td><strong>${sessionScope.user.phoneNumber}</strong></td>
                    </tr>
                    <tr>
                        <th>Vai trò</th>
                        <td><span class="status-badge status-good">${sessionScope.user.role}</span></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html> 