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

    <section class="featured-products">
        <h2>Hệ thống cửa hàng</h2>
        <div class="about-grid">
            <c:forEach var="store" items="${storeList}">
                <div class="about-item">
                    <h3>${store.name}</h3>
                    <p>${store.address}</p>
                    <p>${store.phoneNumber}</p>
                    <p>${store.openingHours}</p>
                </div>
            </c:forEach>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>