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

    <section class="hero home-hero">
        <div class="hero-content">
            <h1>Mới nướng, giao hàng ngày</h1>
            <p>Bánh mì, bánh ngọt thủ công — trực tiếp từ lò nướng đến tận nhà</p>
            <a href="${pageContext.request.contextPath}/products" class="btn-order">Đặt hàng ngay</a>
        </div>
    </section>

    <section class="story-banner">
        <div class="story-inner">
            <div class="story-brand">
                <span>BAKER</span>
                <small>EST.2026</small>
            </div>
            <div class="story-copy">
                <p><strong>BakerShop</strong> là một trong những thương hiệu bánh mới được đông đảo người tiêu dùng yêu thích. Thành công đó có được là nhờ những phấn đấu không ngừng từ một tiệm bánh nhỏ cho đến ngày hôm nay</p>
            </div>
        </div>
    </section>

    <section class="featured-products">
        <h2>Bánh bán chạy nhất</h2>
        <div class="product-grid">
            <c:forEach var="cake" items="${cakeList}">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/statics/images/${cake.localImageFile}" alt="${cake.name}">
                    <h3>${cake.name}</h3>
                    <p class="product-description">${cake.description}</p>
                </div>
            </c:forEach>
        </div>
    </section>

    <section class="about">
        <h2>Vì sao nên chọn chúng tôi</h2>
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
</html>