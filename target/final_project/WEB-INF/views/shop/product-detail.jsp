<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - <c:out value="${cake.name}"/></title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <main class="product-detail-page">
        <a class="product-detail-back" href="${pageContext.request.contextPath}/products">‹ Quay lại thực đơn</a>
        <section class="product-detail-layout">
            <div class="product-detail-image-wrap">
                <img class="product-detail-image" src="<c:out value='${cake.imageUrl}'/>" alt="<c:out value='${cake.name}'/>">
            </div>
            <div class="product-detail-info">
                <p class="cart-eyebrow">BÁNH TƯƠI MỖI NGÀY</p>
                <h1><c:out value="${cake.name}"/></h1>
                <c:if test="${not empty cake.components}">
                    <ul class="product-detail-components">
                        <li>Thành phần: <c:out value="${cake.components}"/></li>
                    </ul>
                </c:if>
                <p class="product-detail-description"><c:out value="${cake.description}"/></p>
                <p class="product-detail-stock">
                    <c:choose>
                        <c:when test="${cake.stockQuantity > 0}">Còn ${cake.stockQuantity} sản phẩm</c:when>
                        <c:otherwise>Hiện đã hết hàng</c:otherwise>
                    </c:choose>
                </p>
                <p class="product-detail-price"><fmt:formatNumber value="${cake.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/></p>

                <c:choose>
                    <c:when test="${cake.stockQuantity > 0}">
                        <form class="product-order-form" action="${pageContext.request.contextPath}/cart" method="post">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="cakeId" value="${cake.id}">
                            <input type="hidden" name="size" value="M">
                            <h2>Chọn thời gian giao hàng</h2>
                            <p>Vui lòng chọn ngày và giờ bạn muốn nhận bánh.</p>
                            <div class="delivery-time-fields">
                                <div>
                                    <label for="deliveryDate">Ngày giao hàng</label>
                                    <input id="deliveryDate" name="deliveryDate" type="date" min="${minimumDeliveryDate}" required>
                                </div>
                                <div>
                                    <label for="deliveryTime">Giờ giao hàng</label>
                                    <input id="deliveryTime" name="deliveryTime" type="time" required>
                                </div>
                            </div>
                            <label for="quantity">Số lượng</label>
                            <input class="product-detail-quantity" id="quantity" name="quantity" type="number" value="1" min="1" max="${cake.stockQuantity}" required>
                            <button class="cart-checkout" type="submit">Thêm vào giỏ hàng</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <button class="cart-checkout" type="button" disabled>Hết hàng</button>
                    </c:otherwise>
                </c:choose>
                <a class="product-review-link" href="${pageContext.request.contextPath}/reviews?cakeId=${cake.id}">Xem đánh giá sản phẩm</a>
            </div>
        </section>
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
