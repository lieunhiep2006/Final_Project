<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Thông tin tài khoản</title>
</head>
<body class="auth-page">
	<main class="auth-shell profile-shell">
		<section class="auth-brand-panel">
			<a class="auth-logo" href="${pageContext.request.contextPath}/home"><i class="fa-solid fa-cake-candles"></i> BakerShop</a>
			<div class="auth-brand-copy">
				<p class="eyebrow">BakerShop</p>
				<h1>Thông tin của bạn.</h1>
				<p>Quản lý thông tin giao hàng để việc đặt bánh trở nên nhanh chóng và thuận tiện hơn.</p>
			</div>
			<i class="fa-solid fa-user-pen auth-watermark" aria-hidden="true"></i>
		</section>
		<section class="auth-form-panel">
			<div class="auth-form-wrap">
				<p class="eyebrow">Tài khoản BakerShop</p>
				<h2>Thông tin tài khoản</h2>
				<p class="auth-intro">Cập nhật thông tin cá nhân và địa chỉ nhận hàng.</p>
				<c:if test="${not empty error}"><p class="auth-message">${error}</p></c:if>
				<c:if test="${not empty success}"><p class="auth-success">${success}</p></c:if>
				<form class="auth-form" action="${pageContext.request.contextPath}/profile" method="post">
					<label for="profile-name">Họ và tên</label>
					<div class="auth-input"><i class="fa-regular fa-id-card"></i><input id="profile-name" type="text" name="fullName" value="${sessionScope.user.fullName}" autocomplete="name" required></div>
					<label for="profile-phone">Số điện thoại</label>
					<div class="auth-input profile-readonly"><i class="fa-solid fa-phone"></i><input id="profile-phone" type="tel" value="${sessionScope.user.phoneNumber}" autocomplete="tel" readonly></div>
					<label for="profile-address">Địa chỉ</label>
					<div class="auth-input"><i class="fa-solid fa-location-dot"></i><input id="profile-address" type="text" name="address" value="${sessionScope.user.address}" autocomplete="street-address"></div>
					<button class="auth-submit" type="submit">Lưu thay đổi <i class="fa-solid fa-check"></i></button>
				</form>
				<section class="password-change-section">
					<h3>Đổi mật khẩu</h3>
					<p class="password-change-intro">Nhập mật khẩu hiện tại và xác nhận mật khẩu mới.</p>
					<form class="auth-form" action="${pageContext.request.contextPath}/profile" method="post">
						<input type="hidden" name="action" value="changePassword">
						<label for="current-password">Mật khẩu hiện tại</label>
						<div class="auth-input">
							<i class="fa-solid fa-lock" aria-hidden="true"></i>
							<input id="current-password" type="password" name="currentPassword" autocomplete="current-password" required>
							<button class="password-visibility-toggle" type="button" aria-label="Hiển thị mật khẩu hiện tại" aria-pressed="false" aria-controls="current-password">
								<i class="fa-solid fa-eye" aria-hidden="true"></i>
							</button>
						</div>
						<label for="new-password">Mật khẩu mới</label>
						<div class="auth-input">
							<i class="fa-solid fa-key" aria-hidden="true"></i>
							<input id="new-password" type="password" name="newPassword" autocomplete="new-password" required>
							<button class="password-visibility-toggle" type="button" aria-label="Hiển thị mật khẩu mới" aria-pressed="false" aria-controls="new-password">
								<i class="fa-solid fa-eye" aria-hidden="true"></i>
							</button>
						</div>
						<label for="confirm-password">Xác nhận mật khẩu mới</label>
						<div class="auth-input"><i class="fa-solid fa-key"></i><input id="confirm-password" type="password" name="confirmPassword" autocomplete="new-password" required></div>
						<button class="auth-submit" type="submit">Cập nhật mật khẩu <i class="fa-solid fa-check"></i></button>
					</form>
				</section>
				<p class="auth-switch"><a href="${pageContext.request.contextPath}/home"><i class="fa-solid fa-arrow-left"></i> Quay lại cửa hàng</a></p>
			</div>
		</section>
	</main>
	<script src="${pageContext.request.contextPath}/statics/js/main.js"></script>
</body>
</html>