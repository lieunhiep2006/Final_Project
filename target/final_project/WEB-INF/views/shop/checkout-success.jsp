<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Đặt hàng thành công</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navbar.jsp" />
	<main class="cart-page checkout-success-page">
		<div class="cart-empty">
			<i class="fa-solid fa-circle-check" aria-hidden="true"></i>
			<h1>Đặt hàng thành công</h1>
			<p>Mã đơn hàng của bạn là #${sessionScope.lastOrderId}.</p>
			<p>Đơn hàng đang ở trạng thái chờ xử lý.</p>
			<a class="cart-checkout" href="${pageContext.request.contextPath}/products">Tiếp tục mua bánh</a>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>