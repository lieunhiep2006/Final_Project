<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Tiệm Bánh Ngọt</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />

    <section class="hero">
        <div class="hero-content">
            <h1>Mới nướng, giao hàng ngày</h1>
            <p>Bánh mì, bánh ngọt thủ công — trực tiếp từ lò nướng đến tận nhà</p>
            <a href="#" class="btn-order">Đặt hàng ngay</a>
        </div>
    </section>

    <section class="featured-products">
        <h2>Bánh bán chạy nhất</h2>
        <div class="product-grid">
            <c:forEach var="cake" items="${cakeList}">
                <div class="product-card">
                    <img src="${cake.imageUrl}" alt="${cake.name}" referrerpolicy="no-referrer">
                    <h3>${cake.name}</h3>
                    <p>${cake.price}</p>
                </div>
            </c:forEach>
        </div>
    </section>

    <section class="about">
        <h2>Nên chọn chúng tôi</h2>
        <div class="about-grid">
            <div class="about-item">
                <h3>Nguyên liệu sạch</h3>
                <p>Nguyên liệu mới hàng ngày bảo đảm an toàn thực phẩm</p>
            </div>
            <div class="about-item">
                <h3>Giao hàng nhanh</h3>
                <p>Giao ngay tận nhà ngay khi bánh vừa ra lò</p>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>>
