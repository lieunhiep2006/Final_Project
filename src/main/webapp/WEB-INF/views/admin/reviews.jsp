<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BakerShop - Quản lý Đánh giá</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
    <style>
        .filter-bar { background: #fff; padding: 15px; margin-bottom: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }
        .filter-bar label { font-weight: bold; margin-right: 10px; }
        .filter-bar select { padding: 6px 12px; border-radius: 4px; border: 1px solid #ccc; font-size: 14px; }
        .star-rating { color: #f39c12; font-weight: bold; }
        .btn-delete { background-color: #e74c3c; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; font-weight: bold; }
        .btn-delete:hover { background-color: #c0392b; }
        .no-data { text-align: center; color: #7f8c8d; padding: 20px; }
    </style>
</head>
<body>

<aside class="sidebar">
    <div class="brand">
        <div class="brand-icon">🍰</div>
        <div>
            <div class="brand-name">BakerShop</div>
            <div class="brand-subtitle">Admin Panel</div>
        </div>
    </div>
    <nav class="sidebar-menu">
            <a class="active" href="<c:url value='/admin/dashboard'/>">
                <span>🏠</span>
                Dashboard
            </a>
            <a href="<c:url value='/admin/manage-cakes'/>">
                <span>🍰</span>
                Manage Cakes
            </a>
            <a href="<c:url value='/admin/manage-orders'/>">
                <span>🛒</span>
                Manage Orders
            </a>
            <a href="<c:url value='/admin/manage-vouchers'/>">
                <span>🎟️</span>
                Manage Vouchers
            </a>
            <a href="<c:url value='/admin/reviews'/>">
                <span>⭐</span>
                Manage Reviews
            </a>
    </nav>
</aside>

<main class="main-content">
    <div class="page-header">
        <div>
            <h1>⭐ Quản lý Đánh giá Khách hàng</h1>
            <p>Kiểm duyệt và quản lý đánh giá sản phẩm của khách hàng.</p>
        </div>
    </div>

    <!-- Thanh lọc theo số sao -->
    <div class="filter-bar">
        <form action="${pageContext.request.contextPath}/admin/reviews" method="get">
            <label for="star">Lọc theo số sao:</label>
            <select name="star" id="star" onchange="this.form.submit()">
                <option value="all">Tất cả sao</option>
                <option value="5" ${selectedStar == '5' ? 'selected' : ''}>5 Sao ⭐⭐⭐⭐⭐</option>
                <option value="4" ${selectedStar == '4' ? 'selected' : ''}>4 Sao ⭐⭐⭐⭐</option>
                <option value="3" ${selectedStar == '3' ? 'selected' : ''}>3 Sao ⭐⭐⭐</option>
                <option value="2" ${selectedStar == '2' ? 'selected' : ''}>2 Sao ⭐⭐</option>
                <option value="1" ${selectedStar == '1' ? 'selected' : ''}>1 Sao ⭐</option>
            </select>
        </form>
    </div>

    <!-- Bảng danh sách đánh giá -->
    <div class="table-wrapper">
        <table class="dashboard-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Khách hàng</th>
                    <th>Mã Bánh (Cake ID)</th>
                    <th>Đánh giá</th>
                    <th>Nội dung bình luận</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="r" items="${reviewList}">
                    <tr>
                        <td>${r.id}</td>
                        <td><b>${r.reviewerName}</b></td>
                        <td>#${r.cakeId}</td>
                        <td class="star-rating">${r.rating} ⭐</td>
                        <td>${r.comment}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/admin/reviews" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa đánh giá này không?');">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${r.id}">
                                <button type="submit" class="btn-delete">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty reviewList}">
                    <tr>
                        <td colspan="6" class="no-data">Không có đánh giá nào phù hợp.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</main>

</body>
</html>