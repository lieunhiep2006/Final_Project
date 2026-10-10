<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Giỏ hàng của bạn</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navbar.jsp" />
	<main class="cart-page">
		<div class="cart-heading">
			<p class="cart-eyebrow">BAKERYSHOP</p>
			<h1>Giỏ hàng của bạn</h1>
			<p>Kiểm tra lại những món bánh bạn đã chọn trước khi đặt hàng.</p>
		</div>
		<c:if test="${not empty sessionScope.cartError}">
			<p class="auth-message">${sessionScope.cartError}</p>
			<c:remove var="cartError" scope="session" />
		</c:if>
		<c:choose>
			<c:when test="${empty cartItems}">
				<div class="cart-empty">
					<i class="fa-solid fa-basket-shopping" aria-hidden="true"></i>
					<h2>Giỏ hàng đang trống</h2>
					<p>Hãy chọn một món bánh để bắt đầu đơn hàng của bạn.</p>
					<a class="cart-checkout" href="${pageContext.request.contextPath}/products">Xem thực đơn bánh</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="cart-layout">
					<section class="cart-items" aria-label="Các sản phẩm trong giỏ hàng">
						<c:forEach var="item" items="${cartItems}">
							<article class="cart-item">
								<img src="${item.cake.imageUrl}" alt="${item.cake.name}">
								<div class="cart-item-info">
									<h2>${item.cake.name}</h2>
									<p>${item.cake.description}</p>
									<form class="cart-quantity-form" action="${pageContext.request.contextPath}/cart" method="post">
										<input type="hidden" name="action" value="update">
										<input type="hidden" name="itemId" value="${item.id}">
										<div class="form-group">
											<label for="quantity-${item.id}">Số lượng:</label>
											<input id="quantity-${item.id}" type="number" name="quantity" value="${item.quantity}" min="1" max="${item.cake.stockQuantity}">
										</div>
										<div class="form-group cart-note-group">
											<label for="note-${item.id}">Ghi chú:</label>
											<input id="note-${item.id}" type="text" name="note" value="${item.note}" placeholder="VD: Bớt ngọt, viết chữ Happy Birthday...">
										</div>
										<div class="form-group cart-size-group">
											<label for="size-${item.id}">Size:</label>
											<input id="size-${item.id}" type="text" name="size" value="${item.size}">
										</div>
										<button type="submit">Cập nhật</button>
									</form>
								</div>
								<div class="cart-item-actions">
									<strong class="cart-line-total"><fmt:formatNumber value="${item.lineTotal}" type="currency" currencySymbol="₫" maxFractionDigits="0"/></strong>
									<form action="${pageContext.request.contextPath}/cart" method="post">
										<input type="hidden" name="action" value="remove">
										<input type="hidden" name="itemId" value="${item.id}">
										<button class="cart-remove" type="submit">Xóa</button>
									</form>
								</div>
							</article>
						</c:forEach>
					</section>
					<aside class="cart-summary">
						<h2>Tóm tắt đơn hàng</h2>
						<div class="summary-row"><span>Tạm tính</span><strong>${cartTotal} đ</strong></div>
						<div class="summary-row"><span>Phí giao hàng</span><strong>FREESHIP</strong></div>
						<div class="summary-divider"></div>
						<div class="summary-row summary-total"><span>Tổng cộng</span><strong>${cartTotal} đ</strong></div>
						<form action="${pageContext.request.contextPath}/cart" method="post">
							<input type="hidden" name="action" value="clear">
							<button class="cart-clear" type="submit">Xóa toàn bộ giỏ hàng</button>
						</form>
						<a class="cart-checkout" href="${pageContext.request.contextPath}/checkout">Tiến hành đặt hàng</a>
						<a class="continue-shopping" href="${pageContext.request.contextPath}/products">Tiếp tục chọn bánh</a>
					</aside>
				</div>
			</c:otherwise>
		</c:choose>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
