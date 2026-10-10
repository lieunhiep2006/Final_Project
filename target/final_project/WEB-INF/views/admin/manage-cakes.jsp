<%@ page contentType="text/html; charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>BakerShop - Quản lý bánh</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/css/admin.css">
</head>

<body>

<aside class="sidebar">

    <div class="brand">

        <div class="brand-icon">
            🍰
        </div>

        <div>
            <div class="brand-name">
                BakerShop
            </div>

            <div class="brand-subtitle">
                Admin Panel
            </div>
        </div>

    </div>

    <nav class="sidebar-menu">

        <a href="<c:url value='/admin/dashboard'/>">
            <span>🏠</span>
            Dashboard
        </a>

        <a class="active"
           href="<c:url value='/admin/manage-cakes'/>">
            <span>🍰</span>
            Manage Cakes
        </a>

        <a href="<c:url value='/admin/manage-orders'/>">
            <span>🛒</span>
            Manage Orders
        </a>

    </nav>

</aside>

<main class="main-content">

    <div class="page-header">

        <div>

            <h1>
                🍰 Quản lý bánh
            </h1>

            <p>
                Quản lý các sản phẩm bánh trong hệ thống.
            </p>

        </div>

    </div>

    <section class="form-card">

        <div class="section-title">

            <span class="title-icon">
                ${empty editCake ? '+' : '✏'}
            </span>

            <h2>
                ${empty editCake ? 'Thêm bánh mới' : 'Chỉnh sửa bánh'}
            </h2>

        </div>

        <form method="post"
              action="<c:url value='/admin/manage-cakes'/>"
              class="cake-form">

            <input type="hidden"
                   name="action"
                   value="${empty editCake ? 'insert' : 'update'}">

            <c:if test="${not empty editCake}">

                <input type="hidden"
                       name="id"
                       value="${editCake.id}">

            </c:if>

            <div class="form-group">

                <label>
                    Tên bánh
                </label>

                <input type="text"
                       name="name"
                       placeholder="Nhập tên bánh"
                       value="${editCake.name}"
                       required>

            </div>

            <div class="form-group">

                <label>
                    Mô tả
                </label>

                <input type="text"
                       name="description"
                       placeholder="Nhập mô tả bánh"
                       value="${editCake.description}"
                       required>

            </div>

            <div class="form-group">

                <label>
                    Giá (VNĐ)
                </label>

                <input type="number"
                       name="price"
                       placeholder="Ví dụ: 350000"
                       value="${editCake.price}"
                       min="0"
                       required>

            </div>

            <div class="form-group">

                <label>
                    Tồn kho
                </label>

                <input type="number"
                       name="stockQuantity"
                       placeholder="Ví dụ: 10"
                       value="${editCake.stockQuantity}"
                       min="0"
                       required>

            </div>

            <div class="form-group">

                <label>
                    Category ID
                </label>

                <select name="categoryId"
                        required>

                    <option value="">
                        Chọn danh mục
                    </option>

                    <option value="1"
                            ${editCake.categoryId == 1 ? 'selected' : ''}>
                        Bánh Kem Sinh Nhật
                    </option>

                    <option value="2"
                            ${editCake.categoryId == 2 ? 'selected' : ''}>
                        Bánh Mousse & Bánh Lạnh
                    </option>

                    <option value="3"
                            ${editCake.categoryId == 3 ? 'selected' : ''}>
                        Bánh CheeseCake
                    </option>

                    <option value="4"
                            ${editCake.categoryId == 4 ? 'selected' : ''}>
                        Bánh Khác
                    </option>

                </select>

            </div>

            <div class="form-submit">

                <button type="submit">
                    ${empty editCake ? '＋ Thêm bánh' : '✏ Cập nhật bánh'}
                </button>

            </div>

        </form>

    </section>

    <section class="table-card">

        <div class="table-header">

            <div class="section-title">

                <span class="title-icon">
                    🍰
                </span>

                <h2>
                    Danh sách bánh
                </h2>

            </div>

            <div class="table-tools">

                <form method="get"
                      action="<c:url value='/admin/manage-cakes'/>"
                      class="search-form">

                    <div class="search-box">

                        <span>
                            🔍
                        </span>

                        <input type="text"
                               name="keyword"
                               placeholder="Tìm kiếm bánh..."
                               value="${keyword}">

                    </div>

                    <button type="submit"
                            class="search-button">
                        Tìm kiếm
                    </button>

                </form>

                <select id="categoryFilter">

                    <option value="">
                        Tất cả danh mục
                    </option>

                    <option value="1">
                        Category 1
                    </option>

                    <option value="2">
                        Category 2
                    </option>

                    <option value="3">
                        Category 3
                    </option>

                    <option value="4">
                        Category 4
                    </option>

                </select>

            </div>

        </div>

        <div class="table-wrapper">

            <table class="cake-table">

                <thead>

                <tr>

                    <th>
                        ID
                    </th>

                    <th>
                        TÊN BÁNH
                    </th>

                    <th>
                        MÔ TẢ
                    </th>

                    <th>
                        GIÁ
                    </th>

                    <th>
                        TỒN KHO
                    </th>

                    <th>
                        CATEGORY
                    </th>

                    <th>
                        THAO TÁC
                    </th>

                </tr>

                </thead>

                <tbody id="cakeTableBody">

                <c:forEach var="cake"
                           items="${cakes}"
                           varStatus="status">

                    <tr data-category="${cake.categoryId}">

                        <td>
                            ${status.index + 1}
                        </td>

                        <td>

                            <div class="cake-name">

                                <div class="cake-image">
                                    🍰
                                </div>

                                <span>
                                    ${cake.name}
                                </span>

                            </div>

                        </td>

                        <td class="description">
                            ${cake.description}
                        </td>

                        <td class="price">

                            <fmt:formatNumber
                                    value="${cake.price}"
                                    type="number"
                                    groupingUsed="true"/>

                            đ

                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${cake.stockQuantity > 0}">

                                    <div class="stock">

                                        <span class="stock-badge available">
                                            Còn hàng
                                        </span>

                                        <span class="stock-number">
                                            ${cake.stockQuantity}
                                        </span>

                                    </div>

                                </c:when>

                                <c:otherwise>

                                    <span class="stock-badge sold-out">
                                        Hết hàng
                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>

                            <span class="category-badge">
                                ${cake.categoryId}
                            </span>

                        </td>

                        <td>

                            <div class="actions">

                                <a class="btn-edit"
                                   href="<c:url value='/admin/manage-cakes?action=edit&id=${cake.id}'/>">
                                    ✏ Sửa
                                </a>

                                <a class="btn-delete"
                                   href="<c:url value='/admin/manage-cakes?action=delete&id=${cake.id}'/>"
                                   onclick="return confirm('Bạn có chắc muốn xóa bánh này không?');">
                                    🗑 Xóa
                                </a>

                            </div>

                        </td>

                    </tr>

                </c:forEach>

                <c:if test="${empty cakes}">

                    <tr>

                        <td colspan="7"
                            class="empty-data">

                            Không có dữ liệu bánh.

                        </td>

                    </tr>

                </c:if>

                </tbody>

            </table>

        </div>

        <div class="table-footer">

            <span>
                Hiển thị ${cakes.size()} sản phẩm
            </span>

        </div>

    </section>

</main>

<script src="${pageContext.request.contextPath}/statics/js/main.js"></script>

<script>

    document.getElementById("categoryFilter").addEventListener("change", function () {

        const selectedCategory = this.value;

        const rows = document.querySelectorAll(
            "#cakeTableBody tr[data-category]"
        );

        rows.forEach(function (row) {

            const category = row.getAttribute("data-category");

            if (selectedCategory === "" ||
                category === selectedCategory) {

                row.style.display = "";

            } else {

                row.style.display = "none";

            }

        });

    });

</script>

</body>

</html>