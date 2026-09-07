<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<title>BakeryShop - Sản phẩm</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navbar.jsp" />

	<main class="featured-products product-list-page">
		<h1>Sản phẩm của BakeryShop</h1>
		<p class="product-list-intro">Những món bánh thủ công được làm mới mỗi ngày từ nguyên liệu chọn lọc.</p>
		<div class="product-grid">
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/basquecheesecake.jpg" alt="Bánh Basque cheesecake">
				<h2>Bánh Basque cheesecake</h2>
				<p>Mặt bánh cháy nhẹ, bên trong mềm mịn và béo thơm.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/bagels.jpg" alt="Bánh Bagel">
				<h2>Bánh Bagel</h2>
				<p>Vỏ dai nhẹ, ruột mềm, thích hợp cho bữa sáng nhanh gọn.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/bluefudge.jpg" alt="Bánh fudge việt quất">
				<h2>Bánh fudge việt quất</h2>
				<p>Vị chocolate đậm đà kết hợp cùng chút chua thanh của việt quất.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/brownies.jpg" alt="Bánh Brownie">
				<h2>Bánh Brownie</h2>
				<p>Bánh chocolate mềm ẩm với hương vị ngọt ngào, đậm đà.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/cookie.jpg" alt="Bánh Cookie">
				<h2>Bánh Cookie</h2>
				<p>Chiếc bánh giòn thơm, nhỏ gọn và phù hợp cho mọi lúc.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/corncake.jpg" alt="Bánh ngô">
				<h2>Bánh ngô</h2>
				<p>Bánh mềm xốp với vị ngô tự nhiên và hương thơm dịu nhẹ.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/creampuffs.jpg" alt="Bánh su kem">
				<h2>Bánh su kem</h2>
				<p>Vỏ bánh nhẹ giòn, nhân kem mịn béo tan ngay trong miệng.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/cupcake.jpg" alt="Bánh Cupcake">
				<h2>Bánh Cupcake</h2>
				<p>Bánh nhỏ mềm xốp, thơm ngọt và dễ dàng thưởng thức.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/lemoncheesecake.jpg" alt="Bánh cheesecake chanh">
				<h2>Bánh cheesecake chanh</h2>
				<p>Cheesecake béo mịn cân bằng bằng hương chanh tươi mát.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/macarons.jpg" alt="Bánh Macaron">
				<h2>Bánh Macaron</h2>
				<p>Vỏ bánh giòn nhẹ, nhân mềm dẻo với màu sắc bắt mắt.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/mangomousse.jpg" alt="Bánh mousse xoài">
				<h2>Bánh mousse xoài</h2>
				<p>Lớp mousse mịn nhẹ với vị xoài chín thơm ngọt.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/matcha.jpg" alt="Bánh Matcha">
				<h2>Bánh Matcha</h2>
				<p>Hương trà xanh thanh dịu hòa cùng vị bánh mềm mịn.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/oreocheesecake.jpg" alt="Bánh Oreo cheesecake">
				<h2>Bánh Oreo cheesecake</h2>
				<p>Cheesecake béo mịn kết hợp cùng vụn Oreo giòn thơm.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/peachmousse.jpg" alt="Bánh mousse đào">
				<h2>Bánh mousse đào</h2>
				<p>Mousse mềm mượt với vị đào dịu ngọt và tươi mát.</p>
			</article>
			<article class="product-card">
				<img src="${pageContext.request.contextPath}/statics/images/strawberrymousse.jpg" alt="Bánh mousse dâu">
				<h2>Bánh mousse dâu</h2>
				<p>Vị dâu chua ngọt nổi bật trên nền mousse nhẹ nhàng.</p>
			</article>
		</div>
	</main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
