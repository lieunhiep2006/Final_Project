<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
</head>
<body>
    <div style="padding: 30px;">
        <h1>🔑 Đổi mật khẩu</h1>
        <p style="color: green;">${message}</p>
        <form action="${pageContext.request.contextPath}/admin/change-password" method="POST">
            <div style="margin-bottom: 15px;">
                <label>Mật khẩu cũ:</label><br>
                <input type="password" name="oldPassword" required style="padding: 8px; width: 300px;">
            </div>
            <div style="margin-bottom: 15px;">
                <label>Mật khẩu mới:</label><br>
                <input type="password" name="newPassword" required style="padding: 8px; width: 300px;">
            </div>
            <button type="submit" style="padding: 10px 20px; background: #007bff; color: white; border: none; cursor: pointer;">Cập nhật mật khẩu</button>
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/admin/dashboard">← Quay lại Dashboard</a>
    </div>
</body>
</html> 