<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <h1>${favoritesPage ? 'Bánh yêu thích' : (empty category.name ? 'Tất cả bánh' : category.name)}</h1>
        <p class="product-list-intro">${favoritesPage ? 'Những chiếc bánh bạn đã lưu để xem lại.' : (empty category.description ? 'Chọn món bánh yêu thích và thêm vào giỏ hàng để đặt nhanh hơn.' : category.description)}</p>
        <c:choose>
        <c:when test="${favoritesPage and empty cakes}">
            <div class="cart-empty">
                <i class="fa-regular fa-heart" aria-hidden="true"></i>
                <h2>Bạn chưa có bánh yêu thích</h2>
                <p>Nhấn vào biểu tượng trái tim ở sản phẩm để lưu bánh tại đây.</p>
                <a class="cart-checkout" href="${pageContext.request.contextPath}/products">Khám phá các loại bánh</a>
            </div>
        </c:when>
        <c:otherwise>
        <div class="product-grid">
            <c:forEach var="cake" items="${cakes}">
                <div class="product-card">
                    <a class="product-card-link" href="${pageContext.request.contextPath}/product?id=${cake.id}" aria-label="Xem chi tiết ${cake.name}">
                        <img src="${cake.imageUrl}" alt="${cake.name}">
                        <h3>${cake.name}</h3>
                    </a>
                    <p class="product-description">${cake.description}</p>
                    <div class="product-card-footer">
                        <div>
                            <p class="product-price"><fmt:formatNumber value="${cake.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/></p>
                            <small class="product-stock">Còn ${cake.stockQuantity} sản phẩm</small>
                        </div>
                        <c:choose>
                            <c:when test="${cake.stockQuantity > 0}">
                                <a class="product-detail-button" href="${pageContext.request.contextPath}/product?id=${cake.id}">Đặt hàng</a>
                            </c:when>
                            <c:otherwise><span class="out-of-stock">Hết hàng</span></c:otherwise>
                        </c:choose>
                    </div>
                    <form class="favorite-form" action="${pageContext.request.contextPath}/favorites" method="post">
                        <input type="hidden" name="cakeId" value="${cake.id}">
                        <input type="hidden" name="returnTo" value="<c:out value='${returnTo}'/>">
                        <button class="favorite-btn${cake.favorited ? ' is-favorited' : ''}" type="submit"
                                aria-label="${cake.favorited ? 'Bỏ yêu thích' : 'Thêm vào yêu thích'} ${cake.name}"
                                aria-pressed="${cake.favorited}">
                            <c:choose>
                                <c:when test="${cake.favorited}">♥</c:when>
                                <c:otherwise>♡</c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                    <a class="product-review-link" href="${pageContext.request.contextPath}/reviews?cakeId=${cake.id}">Xem đánh giá</a>
                </div>
            </c:forEach>
        </div>
        </c:otherwise>
        </c:choose>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>