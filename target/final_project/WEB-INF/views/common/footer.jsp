<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer class="baker-footer">
    <div class="footer-container">
        
        <!-- Cột 1: Giới thiệu tiệm bánh -->
        <div class="footer-col">
            <h3><i class="fa-solid fa-cake-candles me-2"></i>BakerShop</h3>
            <p>Chuyên cung cấp các loại bánh ngọt, bánh sinh nhật và pastry thơm ngon, làm mới mỗi ngày từ nguyên liệu cao cấp nhất.</p>
            <div class="social-icons">
                <a href="#"><i class="fa-brands fa-facebook-f"></i></a>
                <a href="#"><i class="fa-brands fa-instagram"></i></a>
                <a href="#"><i class="fa-brands fa-tiktok"></i></a>
            </div>
        </div>

        <!-- Cột 2: Đường dẫn nhanh -->
        <div class="footer-col">
            <h3>Đường dẫn nhanh</h3>
            <ul class="footer-links">
                <li><a href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
                <li><a href="${pageContext.request.contextPath}/products">Thực đơn bánh</a></li>
                <li><a href="${pageContext.request.contextPath}/stores">Hệ thống cửa hàng</a></li>
                <li><a href="${pageContext.request.contextPath}/cart">Giỏ hàng của bạn</a></li>
            </ul>
        </div>

        <!-- Cột 3: Liên hệ hỗ trợ -->
        <div class="footer-col">
            <h3>Thông tin liên hệ</h3>
            <p><i class="fa-solid fa-location-dot"></i> 123 Đường Bánh Ngọt, Q. 1, TP. Hồ Chí Minh</p>
            <p><i class="fa-solid fa-phone"></i> Hotline: 1900 8888</p>
            <p><i class="fa-solid fa-envelope"></i> Email: support@bakershop.com</p>
            <p><i class="fa-solid fa-clock"></i> Mở cửa: 07:00 - 22:00 hàng ngày</p>
        </div>

    </div>

    <!-- Dòng Copyright -->
    <div class="footer-bottom">
        <p>&copy; 2026 BakerShop. All rights reserved. Designed for Servlet Project.</p>
    </div>
</footer>

<!-- Nhúng file JS dùng chung (cart.js, main.js) -->
<script src="${pageContext.request.contextPath}/statics/js/main.js"></script>