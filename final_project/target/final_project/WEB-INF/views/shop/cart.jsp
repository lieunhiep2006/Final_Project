<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakeryShop - Giỏ hàng</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navbar.jsp" />

	<main class="cart-page">
		<div class="cart-heading">
			<p class="cart-eyebrow">BAKERYSHOP</p>
			<h1>Giỏ hàng của bạn</h1>
			<p>Kiểm tra lại những món bánh bạn đã chọn trước khi đặt hàng.</p>
		</div>

		<div class="cart-layout" id="cartContent">
			<section class="cart-items" aria-label="Các sản phẩm trong giỏ hàng">
				<article class="cart-item" data-price="85000">
					<img src="${pageContext.request.contextPath}/statics/images/creampuffs.jpg" alt="Bánh su kem">
					<div class="cart-item-info">
						<h2>Bánh su kem</h2>
						<p>Vỏ bánh nhẹ giòn, nhân kem mịn béo.</p>
						<button class="cart-remove" type="button">Xóa</button>
					</div>
					<div class="cart-item-actions">
						<strong class="cart-item-price">85.000 đ</strong>
						<div class="quantity-control">
							<button type="button" class="quantity-button" data-action="decrease" aria-label="Giảm số lượng">-</button>
							<input class="quantity-input" type="number" min="1" value="2" aria-label="Số lượng bánh su kem">
							<button type="button" class="quantity-button" data-action="increase" aria-label="Tăng số lượng">+</button>
						</div>
						<strong class="cart-line-total">170.000 đ</strong>
					</div>
				</article>

				<article class="cart-item" data-price="120000">
					<img src="${pageContext.request.contextPath}/statics/images/macarons.jpg" alt="Bánh Macaron">
					<div class="cart-item-info">
						<h2>Bánh Macaron</h2>
						<p>Vỏ bánh giòn nhẹ, nhân mềm dẻo thơm ngon.</p>
						<button class="cart-remove" type="button">Xóa</button>
					</div>
					<div class="cart-item-actions">
						<strong class="cart-item-price">120.000 đ</strong>
						<div class="quantity-control">
							<button type="button" class="quantity-button" data-action="decrease" aria-label="Giảm số lượng">-</button>
							<input class="quantity-input" type="number" min="1" value="1" aria-label="Số lượng bánh Macaron">
							<button type="button" class="quantity-button" data-action="increase" aria-label="Tăng số lượng">+</button>
						</div>
						<strong class="cart-line-total">120.000 đ</strong>
					</div>
				</article>
			</section>

			<aside class="cart-summary">
				<h2>Tóm tắt đơn hàng</h2>
				<div class="summary-row"><span>Tạm tính</span><strong id="cartSubtotal">290.000 đ</strong></div>
				<div class="summary-row"><span>Phí giao hàng</span><strong id="cartShipping">25.000 đ</strong></div>
				<div class="summary-divider"></div>
				<div class="summary-row summary-total"><span>Tổng cộng</span><strong id="cartTotal">315.000 đ</strong></div>
				<button class="cart-checkout" type="button">Tiến hành đặt hàng</button>
				<a class="continue-shopping" href="${pageContext.request.contextPath}/products">Tiếp tục chọn bánh</a>
			</aside>
		</div>

		<div class="cart-empty" id="cartEmpty" hidden>
			<i class="fa-solid fa-basket-shopping" aria-hidden="true"></i>
			<h2>Giỏ hàng đang trống</h2>
			<p>Hãy chọn một món bánh để bắt đầu đơn hàng của bạn.</p>
			<a class="cart-checkout" href="${pageContext.request.contextPath}/products">Xem thực đơn bánh</a>
		</div>
	</main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="${pageContext.request.contextPath}/statics/js/cart.js"></script>
</body>
</html>
