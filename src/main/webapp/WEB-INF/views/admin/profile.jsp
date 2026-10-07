<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin tài khoản Admin</title>
</head>
<body style="padding: 30px;">
    <h1>Thông tin tài khoản Admin</h1>
    <p>Họ tên: <strong>${sessionScope.user.fullName}</strong></p>
    <p>Số điện thoại: <strong>${sessionScope.user.phoneNumber}</strong></p>
    <p>Vai trò: <strong>${sessionScope.user.role}</strong></p>
    <br>
    <a href="${pageContext.request.contextPath}/admin/dashboard">&larr; Quay lại Dashboard</a>
</body>
</html>