<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Đơn hàng của tôi</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navbar.jsp" />
	<main class="orders-page">
		<div class="cart-heading">
			<p class="cart-eyebrow">BAKERSHOP</p>
			<h1>Đơn hàng của tôi</h1>
			<p>Theo dõi các đơn hàng bạn đã đặt tại BakerShop.</p>
		</div>
		<c:choose>
			<c:when test="${empty orders}">
				<div class="cart-empty">
					<i class="fa-solid fa-receipt" aria-hidden="true"></i>
					<h2>Chưa có đơn hàng</h2>
					<p>Các đơn hàng của bạn sẽ xuất hiện ở đây.</p>
					<a class="cart-checkout" href="${pageContext.request.contextPath}/products">Xem thực đơn bánh</a>
				</div>
			</c:when>
			<c:otherwise>
				<section class="order-history-list" aria-label="Lịch sử đơn hàng">
					<c:forEach var="order" items="${orders}">
						<article class="order-history-card">
							<header class="order-history-header">
								<div><strong>Đơn hàng #${order.id}</strong><span>${order.storeName}</span></div>
								<div class="order-status">${order.status}</div>
							</header>
							<div class="order-items-list">
								<c:forEach var="item" items="${order.items}">
									<div class="order-history-item">
										<span>${item.cakeName} x ${item.quantity}</span>
										<strong>${item.lineTotal} đ</strong>
									</div>
								</c:forEach>
							</div>
							<div class="order-history-details">
								<span>Giao đến: ${order.deliveryAddress}</span>
								<span>Thanh toán: ${order.paymentStatus}</span>
								<c:if test="${not empty order.voucherCode}"><span>Voucher: ${order.voucherCode}</span></c:if>
							</div>
							<div class="order-history-total"><span>Tổng cộng</span><strong>${order.totalAmount} đ</strong></div>
						</article>
					</c:forEach>
				</section>
			</c:otherwise>
		</c:choose>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>