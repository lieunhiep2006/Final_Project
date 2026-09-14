<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Về chúng tôi</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <main class="about-page">
        <div class="about-breadcrumb">
            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
            <span>/</span>
            <span>Giới thiệu</span>
        </div>

        <section class="about-feature about-feature-first">
            <img src="${pageContext.request.contextPath}/statics/images/about-story.jpg" alt="Bánh mì mới nướng tại BakeryShop">
            <div class="about-feature-copy">
                <h1>Về BakeryShop</h1>
                <p>BakeryShop bắt đầu từ một căn bếp nhỏ và tình yêu dành cho những mẻ bánh vừa ra lò. Mỗi ngày, chúng tôi chuẩn bị bánh với nguyên liệu chọn lọc, làm thủ công từng bước và giao đến khách hàng khi bánh vẫn giữ được hương thơm tươi mới.</p>
                <a class="about-link" href="${pageContext.request.contextPath}/products">Khám phá bánh</a>
            </div>
        </section>

        <section class="about-statement">
            <p><strong>BAKERSHOP</strong> là nơi những công thức thân quen được làm mới bằng sự tận tâm, từ chiếc bánh nhỏ trong căn bếp đến niềm vui trên bàn ăn của mỗi gia đình.</p>
        </section>

        <section class="about-feature about-feature-reverse">
            <div class="about-feature-copy">
                <h2>Tầm nhìn &amp; sứ mệnh</h2>
                <p>Mục tiêu của BakeryShop là trở thành tiệm bánh được tin yêu nhờ chất lượng ổn định, hương vị gần gũi và trải nghiệm mua hàng chu đáo.</p>
                <p>Chúng tôi ưu tiên nguyên liệu rõ nguồn gốc, hạn chế sản xuất dư thừa và liên tục hoàn thiện công thức để mỗi sản phẩm đều đáng nhớ.</p>
            </div>
            <img src="${pageContext.request.contextPath}/statics/images/homebakery.jpg" alt="Thợ làm bánh chuẩn bị bánh tại BakeryShop">
        </section>

        <section class="about-feature about-feature-last">
            <img src="${pageContext.request.contextPath}/statics/images/bagels.jpg" alt="Các loại bánh mì thủ công của BakeryShop">
            <div class="about-feature-copy">
                <h2>Thủ công mỗi ngày</h2>
                <p>Từ khâu trộn bột, tạo hình đến nướng bánh, mọi công đoạn đều được thực hiện cẩn thận. Chúng tôi tin rằng sự kiên nhẫn của người làm bánh tạo nên khác biệt trong từng miếng bánh.</p>
            </div>
        </section>
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>