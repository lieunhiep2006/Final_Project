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
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/creampuffs.jpg" alt="Bánh su kem"> 
                <h3>Bánh su kem</h3>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/macarons.jpg" alt="Bánh Macaron">
                <h3>Bánh Macaron</h3>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/bluefudge.jpg" alt="Bánh Fudge việt quất">
                <h3>Bánh Fudge việt quất</h3>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/corncake.jpg" alt="Bánh ngô">
                <h3>Bánh Ngô</h3>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/oreocheesecake.jpg" alt="Bánh Oreo Cheesecake">
                <h3>Bánh Oreo Cheesecake</h3>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/cupcake.jpg" alt="Bánh Cupcake">
                <h3>Bánh Cupcake</h3>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/matcha.jpg" alt="Trà Matcha">
                <h3>Trà Matcha</h3>
            </div>

            <div class="product-card">
                <img src="${pageContext.request.contextPath}/statics/images/mangomousse.jpg" alt="Bánh Mousse Xoài">
                <h3>Bánh Mousse Xoài</h3>
            </div>
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