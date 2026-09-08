<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập - Tiệm Bánh</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css" type="text/css">
</head>
<body class="auth-page">
    <main class="auth-card">
        <p class="auth-eyebrow">BAKERSHOP</p>
        <h1>Đăng nhập</h1>

        <c:if test="${not empty error}">
            <p class="auth-message auth-error">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p class="auth-message auth-success">${message}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post" class="auth-form">
            <div class="form-group">
                <label for="phoneNumber">Số điện thoại</label>
                <input id="phoneNumber" type="text" name="phoneNumber" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input id="password" type="password" name="password" required>
            </div>
            <button type="submit">Đăng nhập</button>
        </form>

        <div class="auth-links">
            <a href="${pageContext.request.contextPath}/home" class="secondary-link">← Quay lại trang chủ</a>
            <p class="auth-footer">Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a></p>
        </div>
    </main>
</body>
</html>