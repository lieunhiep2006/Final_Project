<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Đánh giá ${cake.name}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navbar.jsp" />
	<main class="reviews-page">
		<a class="continue-shopping" href="${pageContext.request.contextPath}/products">&larr; Quay lại thực đơn</a>
		<section class="review-product">
			<img src="${pageContext.request.contextPath}/statics/images/${cake.localImageFile}" alt="${cake.name}">
			<div>
				<p class="cart-eyebrow">BAKERSHOP</p>
				<h1>Đánh giá ${cake.name}</h1>
				<p>${cake.description}</p>
				<strong class="product-price">${cake.price} đ</strong>
			</div>
		</section>
		<c:if test="${param.success == 'true'}"><p class="review-message review-success">Đánh giá của bạn đã được lưu.</p></c:if>
		<c:if test="${param.error == 'invalid'}"><p class="review-message">Vui lòng chọn số sao từ 1 đến 5 và nhập nội dung đánh giá.</p></c:if>
		<div class="reviews-layout">
			<section class="review-list">
				<h2>Khách hàng nói gì?</h2>
				<c:choose>
					<c:when test="${empty reviews}"><p class="review-empty">Chưa có đánh giá nào cho sản phẩm này.</p></c:when>
					<c:otherwise>
						<c:forEach var="review" items="${reviews}">
							<article class="review-card">
								<div class="review-card-heading"><strong>${review.reviewerName}</strong><span class="review-stars">${review.rating} / 5</span></div>
								<p>${review.comment}</p>
							</article>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</section>
			<aside class="review-form-panel">
				<h2>Viết đánh giá</h2>
				<c:choose>
					<c:when test="${empty sessionScope.user}">
						<p>Vui lòng đăng nhập để chia sẻ cảm nhận của bạn.</p>
						<a class="cart-checkout" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
					</c:when>
					<c:otherwise>
						<form class="review-form" action="${pageContext.request.contextPath}/reviews" method="post">
							<input type="hidden" name="cakeId" value="${cake.id}">
							<label for="rating">Số sao</label>
							<select id="rating" name="rating" required>
								<option value="">Chọn số sao</option>
								<option value="5">5 - Rất ngon</option>
								<option value="4">4 - Ngon</option>
								<option value="3">3 - Bình thường</option>
								<option value="2">2 - Chưa phù hợp</option>
								<option value="1">1 - Không hài lòng</option>
							</select>
							<label for="comment">Nhận xét</label>
							<textarea id="comment" name="comment" rows="5" maxlength="1000" required></textarea>
							<button class="cart-checkout" type="submit">Gửi đánh giá</button>
						</form>
					</c:otherwise>
				</c:choose>
			</aside>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>