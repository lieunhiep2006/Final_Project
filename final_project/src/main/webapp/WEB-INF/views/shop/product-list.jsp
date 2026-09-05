<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Thực đơn bánh</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />

    <section class="featured-products">
        <h2>Tất cả bánh</h2>
        <div class="product-grid">
            <c:forEach var="cake" items="${cakeList}">
                <div class="product-card">
                    <img src="${cake.imageUrl}" alt="${cake.name}">
                    <h3>${cake.name}</h3>
                    <p>${cake.price} đ</p>
                </div>
            </c:forEach>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
