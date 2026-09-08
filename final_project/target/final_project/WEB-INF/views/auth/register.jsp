<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký Tài Khoản - Baker Shop</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css" type="text/css">
</head>
<body class="auth-page">
    <main class="auth-card auth-card-wide">
        <p class="auth-eyebrow">BAKERSHOP</p>
        <h1>Tạo tài khoản</h1>

        <c:if test="${not empty error}">
            <p class="auth-message auth-error">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post" class="auth-form">
            <div class="form-group">
                <label for="fullName">Họ và tên</label>
                <input id="fullName" type="text" name="fullName" required>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Số điện thoại</label>
                <input id="phoneNumber" type="text" name="phoneNumber" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input id="password" type="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="address">Địa chỉ</label>
                <input id="address" type="text" name="address" required>
            </div>
            <button type="submit">Đăng ký</button>
        </form>

        <div class="auth-links">
            <a href="${pageContext.request.contextPath}/home" class="secondary-link">← Quay lại trang chủ</a>
            <p class="auth-footer">Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
        </div>
    </main>
</body>
</html>