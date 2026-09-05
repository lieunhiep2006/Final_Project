<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <title>BakerShop - Về chúng tôi</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <section class="hero about-hero">
        <div class="hero-content">
            <h1>Từ bếp nhỏ đến tiệm bánh</h1>
            <p>BakerShop bắt đầu từ một nhóm sinh viên, nướng từng mẻ nhỏ cho hàng xóm trước khi trở thành tiệm bánh của ngày hôm nay.</p>
        </div>
    </section>

    <section class="about">
        <h2>Cam kết / Nhiệm vụ</h2>
        <div class="about-grid">
            <div class="about-item">
                <h3>Chất lượng</h3>
                <p>Nguyên liệu mới hàng ngày,số lượng ít, không chất bảo quản.</p>
            </div>
            <div class="about-item">
                <h3>Truyền thống</h3>
                <p>Công thức nấu ăn truyền thống, kỹ thuật thủ công</p>
            </div>
            <div class="about-item">
                <h3>Cộng đồng</h3>
                <p>Nguyên liệu từ người dân địa phương, hỗ trợ người trồng trực tiếp</p>
            </div>
                <div class="about-item">
                <h3>Tận tâm</h3>
                <p>Mọi đơn hàng đều được thực hiện chu đáo, không nướng hàng loạt</p>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>