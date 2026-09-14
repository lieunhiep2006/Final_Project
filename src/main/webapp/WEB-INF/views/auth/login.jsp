<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakerShop - Đăng nhập</title>
</head>
<body class="auth-page">
	<main class="auth-shell">
		<section class="auth-brand-panel">
			<a class="auth-logo" href="${pageContext.request.contextPath}/home"><i class="fa-solid fa-cake-candles"></i> BakerShop</a>
			<div class="auth-brand-copy">
				<p class="eyebrow">Welcome back</p>
				<h1>Hương vị quen thuộc đang chờ bạn.</h1>
				<p>Đăng nhập để theo dõi đơn hàng và lưu lại những chiếc bánh yêu thích.</p>
			</div>
			<i class="fa-solid fa-wand-magic-sparkles auth-watermark" aria-hidden="true"></i>
		</section>
		<section class="auth-form-panel">
			<div class="auth-form-wrap">
				<p class="eyebrow">Tài khoản BakerShop</p>
				<h2>Đăng nhập</h2>
				<p class="auth-intro">Chào mừng bạn quay trở lại.</p>
				<c:if test="${param.registered == 'true'}"><p class="auth-success">Tạo tài khoản thành công. Hãy đăng nhập.</p></c:if>
				<c:if test="${not empty error}"><p class="auth-message">${error}</p></c:if>
				<form class="auth-form" action="${pageContext.request.contextPath}/login" method="post">
					<label for="login-phone">Số điện thoại</label>
					<div class="auth-input"><i class="fa-solid fa-phone"></i><input id="login-phone" type="tel" name="phoneNumber" autocomplete="tel" required></div>
					<label for="login-password">Mật khẩu</label>
					<div class="auth-input"><i class="fa-solid fa-lock"></i><input id="login-password" type="password" name="password" autocomplete="current-password" required></div>
					<button class="auth-submit" type="submit">Đăng nhập <i class="fa-solid fa-arrow-right"></i></button>
				</form>
				<p class="auth-switch">Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a></p>
			</div>
		</section>
	</main>
</body>
</html>
