<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Cửa hàng</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />

    <main class="featured-products store-list-page">
        <h2>Hệ thống cửa hàng</h2>
        <div class="about-grid">
            <c:forEach var="store" items="${storeList}">
                <article class="about-item store-card">
                    <img src="${pageContext.request.contextPath}/statics/images/bakery1.jpg" alt="${store.name}">
                    <div class="store-card-content">
                        <h3>${store.name}</h3>
                        <p><strong>Địa chỉ:</strong> ${store.address}</p>
                        <p><strong>Số điện thoại:</strong> ${store.phoneNumber}</p>
                        <p><strong>Giờ mở cửa:</strong> ${store.openingHours}</p>
                    </div>
                </article>
            </c:forEach>
        </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>