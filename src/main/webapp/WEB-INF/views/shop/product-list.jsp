<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Thực đơn bánh</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />

    <section class="featured-products product-list-page">
        <p class="cart-eyebrow">Fresh from the oven</p>
        <h1>Tất cả bánh</h1>
        <p class="product-list-intro">Chọn món bánh yêu thích và thêm vào giỏ hàng để đặt nhanh hơn.</p>
        <div class="product-grid">
            <c:forEach var="cake" items="${cakeList}">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/statics/images/${cake.localImageFile}" alt="${cake.name}">
                    <h3>${cake.name}</h3>
                    <p class="product-description">${cake.description}</p>
                    <div class="product-card-footer">
                        <div>
                            <p class="product-price">${cake.price} đ</p>
                            <small class="product-stock">Còn ${cake.stockQuantity} sản phẩm</small>
                        </div>
                        <c:choose>
                            <c:when test="${cake.stockQuantity > 0}">
                                <form action="${pageContext.request.contextPath}/cart" method="post">
                                    <input type="hidden" name="cakeId" value="${cake.id}">
                                    <input type="hidden" name="quantity" value="1">
                                    <button class="add-to-cart-btn" type="submit" title="Thêm vào giỏ hàng" aria-label="Thêm ${cake.name} vào giỏ hàng"><i class="fa-solid fa-cart-plus"></i></button>
                                </form>
                            </c:when>
                            <c:otherwise><span class="out-of-stock">Hết hàng</span></c:otherwise>
                        </c:choose>
                    </div>
                    <a class="product-review-link" href="${pageContext.request.contextPath}/reviews?cakeId=${cake.id}">Xem đánh giá</a>
                </div>
            </c:forEach>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>