<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Đăng ký</title>
</head>
<body class="auth-page">
	<main class="auth-shell auth-shell-register">
		<section class="auth-brand-panel">
			<a class="auth-logo" href="${pageContext.request.contextPath}/home"><i class="fa-solid fa-cake-candles"></i> BakerShop</a>
			<div class="auth-brand-copy">
				<p class="eyebrow">Join the bakery</p>
				<h1>Một tài khoản, cả thế giới bánh ngọt.</h1>
				<p>Tạo tài khoản để trải nghiệm đặt bánh nhanh chóng và thuận tiện hơn.</p>
			</div>
			<i class="fa-solid fa-cookie-bite auth-watermark" aria-hidden="true"></i>
		</section>
		<section class="auth-form-panel">
			<div class="auth-form-wrap">
				<p class="eyebrow">Bắt đầu cùng BakerShop</p>
				<h2>Tạo tài khoản</h2>
				<p class="auth-intro">Điền thông tin để trở thành thành viên.</p>
				<c:if test="${not empty error}"><p class="auth-message">${error}</p></c:if>
				<form class="auth-form" action="${pageContext.request.contextPath}/register" method="post">
					<label for="register-name">Họ và tên</label>
					<div class="auth-input"><i class="fa-regular fa-id-card"></i><input id="register-name" type="text" name="fullName" autocomplete="name" required></div>
					<label for="register-phone">Số điện thoại</label>
					<div class="auth-input"><i class="fa-solid fa-phone"></i><input id="register-phone" type="tel" name="phoneNumber" autocomplete="tel" required></div>
					<label for="register-address">Địa chỉ</label>
					<div class="auth-input"><i class="fa-solid fa-location-dot"></i><input id="register-address" type="text" name="address" autocomplete="street-address"></div>
					<div class="auth-form-row">
						<div><label for="register-password">Mật khẩu</label><div class="auth-input"><i class="fa-solid fa-lock"></i><input id="register-password" type="password" name="password" autocomplete="new-password" required></div></div>
						<div><label for="register-confirm">Xác nhận mật khẩu</label><div class="auth-input"><i class="fa-solid fa-shield-halved"></i><input id="register-confirm" type="password" name="confirmPassword" autocomplete="new-password" required></div></div>
					</div>
					<button class="auth-submit" type="submit">Tạo tài khoản <i class="fa-solid fa-arrow-right"></i></button>
				</form>
				<p class="auth-switch">Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
			</div>
		</section>
	</main>
</body>
</html>
