<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <!-- 1. Nhúng Header (CSS & Fonts) -->
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Tiệm Bánh Ngọt</title>
</head>
<body>

    <!-- 2. Nhúng Thanh Điều Hướng (Navbar) -->
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />

    <!-- Nội dung tạm thời để test giao diện -->
    <main style="max-width: 1200px; margin: 40px auto; padding: 0 20px; min-height: 400px;">
        <h1 style="color: var(--primary-color);">Chào mừng đến với BakerShop!</h1>
        <p>Giao diện khung (Header, Navigation, Footer) đã được kết nối thành công.</p>
    </main>

    <!-- 3. Nhúng Chân Trang (Footer) -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>