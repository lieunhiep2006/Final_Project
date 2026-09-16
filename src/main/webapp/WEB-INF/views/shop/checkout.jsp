<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Xác nhận đơn hàng</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navbar.jsp" />
	<main class="cart-page checkout-page">
		<div class="cart-heading">
			<p class="cart-eyebrow">BAKERSHOP</p>
			<h1>Xác nhận đơn hàng</h1>
			<p>Kiểm tra giỏ hàng và áp dụng mã giảm giá trước khi đặt bánh.</p>
		</div>
		<c:if test="${not empty sessionScope.checkoutMessage}">
			<p class="auth-message">${sessionScope.checkoutMessage}</p>
			<c:remove var="checkoutMessage" scope="session" />
		</c:if>
		<div class="cart-layout">
			<section class="cart-items" aria-label="Các sản phẩm trong đơn hàng">
				<c:forEach var="item" items="${cartItems}">
					<article class="cart-item">
						<img src="${pageContext.request.contextPath}/statics/images/${item.cake.localImageFile}" alt="${item.cake.name}">
						<div class="cart-item-info">
							<h2>${item.cake.name}</h2>
							<p>${item.cake.description}</p>
							<p class="checkout-quantity">Số lượng: ${item.quantity}</p>
						</div>
						<div class="cart-item-actions">
							<strong class="cart-line-total">${item.cake.price * item.quantity} đ</strong>
						</div>
					</article>
				</c:forEach>
				<a class="continue-shopping" href="${pageContext.request.contextPath}/cart">Quay lại giỏ hàng</a>
			</section>
			<aside class="cart-summary">
				<h2>Tóm tắt đơn hàng</h2>
				<form class="voucher-form" action="${pageContext.request.contextPath}/checkout" method="post">
					<label for="voucherCode">Mã giảm giá</label>
					<div class="voucher-input-row">
						<input id="voucherCode" name="voucherCode" type="text" value="${voucher.code}" placeholder="Nhập mã voucher" maxlength="50">
						<button type="submit">Áp dụng</button>
					</div>
				</form>
				<c:if test="${not empty voucher}">
					<p class="voucher-applied">Đã áp dụng: <strong>${voucher.code}</strong></p>
				</c:if>
				<div class="summary-row"><span>Tạm tính</span><strong>${cartTotal} đ</strong></div>
				<div class="summary-row"><span>Giảm giá</span><strong class="summary-discount">-${discountAmount} đ</strong></div>
				<div class="summary-row"><span>Phí giao hàng</span><strong>0 đ</strong></div>
				<div class="summary-divider"></div>
				<div class="summary-row summary-total"><span>Tổng cộng</span><strong>${finalTotal} đ</strong></div>
				<form class="order-form" action="${pageContext.request.contextPath}/checkout" method="post">
					<input type="hidden" name="action" value="placeOrder">
					<label for="storeId">Cửa hàng nhận đơn</label>
					<select id="storeId" name="storeId" required>
						<option value="">Chọn cửa hàng</option>
						<c:forEach var="store" items="${storeList}">
							<option value="${store.id}">${store.name} - ${store.address}</option>
						</c:forEach>
					</select>
					<label for="deliveryAddress">Địa chỉ giao hàng</label>
					<input id="deliveryAddress" name="deliveryAddress" type="text" value="${user.address}" maxlength="255" required>
					<label for="deliveryPhone">Số điện thoại</label>
					<input id="deliveryPhone" name="deliveryPhone" type="tel" value="${user.phoneNumber}" maxlength="20" required>
					<fieldset class="payment-methods">
						<legend>Phương thức thanh toán</legend>
						<label class="payment-option">
							<input type="radio" name="paymentMethod" value="momo" required>
							<span><strong>MoMo</strong><small>Thanh toán qua ví MoMo</small></span>
						</label>
						<label class="payment-option">
							<input type="radio" name="paymentMethod" value="onlineBanking" required>
							<span><strong>Online Banking</strong><small>Chuyển khoản ngân hàng</small></span>
						</label>
					</fieldset>
					<button class="cart-checkout payment-submit" type="submit" disabled>Tiến hành thanh toán</button>
				</form>
			</aside>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
		const paymentOptions = document.querySelectorAll('input[name="paymentMethod"]');
		const paymentSubmit = document.querySelector('.payment-submit');
		paymentOptions.forEach(function (option) {
			option.addEventListener('change', function () {
				paymentSubmit.disabled = false;
			});
		});
	</script>
</body>
</html>
