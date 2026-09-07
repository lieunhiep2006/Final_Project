<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Cửa hàng</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />

    <main class="featured-products store-list-page">
        <h2>Hệ thống cửa hàng</h2>
        <div class="about-grid">
            <article class="about-item store-card">
                <img src="${pageContext.request.contextPath}/statics/images/bakery1.jpg" alt="Sweet Cake - Chi nhánh 1">
                <div class="store-card-content">
                    <h3>Sweet Cake - Chi nhánh 1</h3>
                    <p><strong>Địa chỉ:</strong> 123 Đường Võ Văn Ngân, Thủ Đức, TP.HCM</p>
                    <p><strong>Số điện thoại:</strong> 0283899999</p>
                    <p><strong>Giờ mở cửa:</strong> 07:30 - 21:30</p>
                </div>
            </article>
            <article class="about-item store-card">
                <img src="${pageContext.request.contextPath}/statics/images/bakery2.jpg" alt="Sweet Cake - Chi nhánh 2">
                <div class="store-card-content">
                    <h3>Sweet Cake - Chi nhánh 2</h3>
                    <p><strong>Địa chỉ:</strong> 456 Đường Nguyễn Thị Minh Khai, Quận 3, TP.HCM</p>
                    <p><strong>Số điện thoại:</strong> 0283888888</p>
                    <p><strong>Giờ mở cửa:</strong> 08:00 - 22:00</p>
                </div>
            </article>
        </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>