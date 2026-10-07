<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BakerShop - Dashboard</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/css/admin.css">
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

    </nav>

</aside>

<main class="main-content">

    <div class="page-header">

        <div>
            <h1>👋 Xin chào, Admin!</h1>
            <p>Chào mừng bạn quay lại BakerShop.</p>
        </div>

        <div class="admin-user">

            <div class="notification-wrapper">

                <button type="button"
                        class="notification"
                        onclick="toggleNotifications()">
                    🔔
                </button>

                <div class="notification-dropdown"
                     id="notificationDropdown">

                    <div class="notification-header">
                        <strong>Thông báo</strong>
                    </div>

                    <c:choose>

                        <c:when test="${pendingOrders > 0}">

                            <a href="${pageContext.request.contextPath}/admin/manage-orders"
                               class="notification-item">

                                <span class="notification-icon">🛒</span>

                                <span>
                                    Có ${pendingOrders} đơn hàng cần xử lý.
                                </span>

                            </a>

                        </c:when>

                        <c:otherwise>

                            <div class="notification-item notification-empty">
                                Không có đơn hàng mới.
                            </div>

                        </c:otherwise>

                    </c:choose>

                    <c:if test="${lowStock > 0}">

                        <a href="${pageContext.request.contextPath}/admin/manage-cakes"
                           class="notification-item">

                            <span class="notification-icon">⚠️</span>

                            <span>
                                Có ${lowStock} bánh sắp hết hàng.
                            </span>

                        </a>

                    </c:if>

                    <c:if test="${outOfStock > 0}">

                        <a href="${pageContext.request.contextPath}/admin/manage-cakes"
                           class="notification-item">

                            <span class="notification-icon">❌</span>

                            <span>
                                Có ${outOfStock} bánh đã hết hàng.
                            </span>

                        </a>

                    </c:if>

                </div>

            </div>

            <div class="admin-account">

                <button type="button"
                        class="admin-account-button"
                        onclick="toggleAdminMenu()">

                    <div class="avatar">👤</div>

                    <span>Admin</span>

                    <span class="admin-arrow">▼</span>

                </button>

                <div class="admin-dropdown"
                     id="adminDropdown">

                    <a href="${pageContext.request.contextPath}/admin/profile"
                       class="admin-dropdown-item">

                        <span>👤</span>
                        <span>Thông tin tài khoản</span>

                    </a>

                    <a href="${pageContext.request.contextPath}/admin/change-password"
                       class="admin-dropdown-item">

                        <span>🔑</span>
                        <span>Đổi mật khẩu</span>

                    </a>

                    <a href="${pageContext.request.contextPath}/admin/settings"
                       class="admin-dropdown-item">

                        <span>⚙️</span>
                        <span>Cài đặt</span>

                    </a>

                    <div class="admin-dropdown-divider"></div>

                    <a href="${pageContext.request.contextPath}/logout"
                       class="admin-dropdown-item logout-item">

                        <span>🚪</span>
                        <span>Đăng xuất</span>

                    </a>

                </div>

            </div>

        </div>

    </div>


    <div class="dashboard-stats">

        <div class="stat-card orange">

            <div class="stat-icon">🍰</div>

            <div>
                <p>Tổng loại bánh</p>
                <h2>${totalCakes}</h2>
                <span>sản phẩm</span>
            </div>

        </div>


        <div class="stat-card green">

            <div class="stat-icon">📦</div>

            <div>
                <p>Tổng tồn kho</p>
                <h2>${totalStock}</h2>
                <span>chiếc bánh</span>
            </div>

        </div>


        <div class="stat-card red">

            <div class="stat-icon">⚠️</div>

            <div>
                <p>Sắp hết hàng</p>
                <h2>${lowStock}</h2>
                <span>sản phẩm</span>
            </div>

        </div>


        <div class="stat-card purple">

            <div class="stat-icon">🏪</div>

            <div>
                <p>Trạng thái cửa hàng</p>
                <h2>Hoạt động</h2>
                <span>● Đang mở</span>
            </div>

        </div>

    </div>


    <section class="dashboard-card">

        <div class="dashboard-card-header">

            <div>
                <h2>🍰 Sản phẩm hiện tại</h2>
                <p>Danh sách bánh đang có trong hệ thống</p>
            </div>

            <a href="<c:url value='/admin/manage-cakes'/>"
               class="view-all">
                Xem tất cả →
            </a>

        </div>


        <div class="table-wrapper">

            <table class="dashboard-table">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Tên bánh</th>
                    <th>Giá</th>
                    <th>Tồn kho</th>
                    <th>Category</th>
                    <th>Trạng thái</th>

                </tr>

                </thead>


                <tbody>

                <c:forEach var="cake"
                           items="${cakes}"
                           varStatus="status"
                           begin="0"
                           end="6">

                    <tr>

                        <td>${status.index + 1}</td>

                        <td>

                            <div class="dashboard-cake-name">

                                <div class="small-cake-icon">
                                    🍰
                                </div>

                                ${cake.name}

                            </div>

                        </td>

                        <td class="dashboard-price">

                            <fmt:formatNumber
                                value="${cake.price}"
                                type="number"
                                groupingUsed="true"/>

                            đ

                        </td>

                        <td>
                            ${cake.stockQuantity}
                        </td>

                        <td>

                            <span class="category-badge">
                                ${cake.categoryId}
                            </span>

                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${cake.stockQuantity > 5}">

                                    <span class="status-badge status-good">
                                        Còn hàng
                                    </span>

                                </c:when>

                                <c:when test="${cake.stockQuantity > 0}">

                                    <span class="status-badge status-warning">
                                        Sắp hết
                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <span class="status-badge status-danger">
                                        Hết hàng
                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>

                    </tr>

                </c:forEach>

                </tbody>

            </table>

        </div>

    </section>


    <section class="dashboard-card">

        <div class="dashboard-card-header">

            <div>
                <h2>🛒 Đơn hàng gần đây</h2>
                <p>Các đơn hàng mới nhất trong hệ thống</p>
            </div>

            <a href="<c:url value='/admin/manage-orders'/>"
               class="view-all">
                Xem tất cả →
            </a>

        </div>


        <div class="table-wrapper">

            <table class="dashboard-table">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Khách hàng</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Thanh toán</th>

                </tr>

                </thead>


                <tbody>

                <c:forEach var="order"
                           items="${orders}"
                           varStatus="status"
                           begin="0"
                           end="4">

                    <tr>

                        <td>
                            #${order.id}
                        </td>

                        <td>
                            ${order.customerName}
                        </td>

                        <td class="dashboard-price">

                            <fmt:formatNumber
                                value="${order.totalAmount}"
                                type="number"
                                groupingUsed="true"/>

                            đ

                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${order.status == 'Pending'}">

                                    <span class="status-badge status-warning">
                                        Chờ xử lý
                                    </span>

                                </c:when>

                                <c:when test="${order.status == 'Delivering'}">

                                    <span class="status-badge status-good">
                                        Đang giao
                                    </span>

                                </c:when>

                                <c:when test="${order.status == 'Completed'}">

                                    <span class="status-badge status-good">
                                        Hoàn thành
                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <span class="status-badge status-danger">
                                        Đã hủy
                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>


                        <td>

                            <c:choose>

                                <c:when test="${order.paymentStatus == 'Paid'}">

                                    <span class="status-badge status-good">
                                        Đã thanh toán
                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <span class="status-badge status-warning">
                                        Chưa thanh toán
                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>

                    </tr>

                </c:forEach>


                <c:if test="${empty orders}">

                    <tr>

                        <td colspan="5"
                            style="text-align: center;">

                            Chưa có đơn hàng nào.

                        </td>

                    </tr>

                </c:if>

                </tbody>

            </table>

        </div>

    </section>


    <section class="dashboard-card">

        <div class="dashboard-card-header">

            <div>
                <h2>💰 Tổng quan doanh thu</h2>
                <p>Tình hình doanh thu của cửa hàng</p>
            </div>

        </div>


        <div class="dashboard-stats">

            <div class="stat-card green">

                <div class="stat-icon">💰</div>

                <div>

                    <p>Tổng doanh thu</p>

                    <h2>

                        <fmt:formatNumber
                            value="${totalRevenue}"
                            type="number"
                            groupingUsed="true"/>

                        đ

                    </h2>

                    <span>Tất cả đơn hàng</span>

                </div>

            </div>


            <div class="stat-card purple">

                <div class="stat-icon">💵</div>

                <div>

                    <p>Đã thanh toán</p>

                    <h2>

                        <fmt:formatNumber
                            value="${paidRevenue}"
                            type="number"
                            groupingUsed="true"/>

                        đ

                    </h2>

                    <span>Đơn đã thanh toán</span>

                </div>

            </div>


            <div class="stat-card orange">

                <div class="stat-icon">✅</div>

                <div>

                    <p>Đơn hoàn thành</p>

                    <h2>${completedOrders}</h2>

                    <span>đơn hàng</span>

                </div>

            </div>

        </div>


        <div class="table-wrapper">

            <table class="dashboard-table">

                <thead>

                <tr>

                    <th>Trạng thái</th>
                    <th>Số tiền</th>

                </tr>

                </thead>


                <tbody>

                <tr>

                    <td>
                        <span class="status-badge status-good">
                            Hoàn thành
                        </span>
                    </td>

                    <td class="dashboard-price">

                        <fmt:formatNumber
                            value="${completedRevenue}"
                            type="number"
                            groupingUsed="true"/>

                        đ

                    </td>

                </tr>


                <tr>

                    <td>
                        <span class="status-badge status-good">
                            Đang giao
                        </span>
                    </td>

                    <td class="dashboard-price">

                        <fmt:formatNumber
                            value="${deliveringRevenue}"
                            type="number"
                            groupingUsed="true"/>

                        đ

                    </td>

                </tr>


                <tr>

                    <td>
                        <span class="status-badge status-warning">
                            Chờ xử lý
                        </span>
                    </td>

                    <td class="dashboard-price">

                        <fmt:formatNumber
                            value="${pendingRevenue}"
                            type="number"
                            groupingUsed="true"/>

                        đ

                    </td>

                </tr>


                <tr>

                    <td>
                        <span class="status-badge status-danger">
                            Đã hủy
                        </span>
                    </td>

                    <td class="dashboard-price">

                        <fmt:formatNumber
                            value="${cancelledRevenue}"
                            type="number"
                            groupingUsed="true"/>

                        đ

                    </td>

                </tr>

                </tbody>

            </table>

        </div>

    </section>


    <div class="dashboard-bottom">

        <div class="quick-card">

            <div class="quick-icon">➕</div>

            <div>
                <h3>Thêm sản phẩm mới</h3>
                <p>Thêm một loại bánh mới vào cửa hàng.</p>
            </div>

            <a href="<c:url value='/admin/manage-cakes'/>">
                Thêm bánh →
            </a>

        </div>


        <div class="quick-card warning-card">

            <div class="quick-icon">⚠️</div>

            <div>
                <h3>Kiểm tra tồn kho</h3>

                <p>
                    Có ${lowStock} sản phẩm đang có số lượng thấp.
                </p>

            </div>

            <a href="<c:url value='/admin/manage-cakes'/>">
                Kiểm tra →
            </a>

        </div>

    </div>

</main>


<script>

    function toggleNotifications() {

        const notification =
            document.getElementById("notificationDropdown");

        const adminMenu =
            document.getElementById("adminDropdown");

        notification.classList.toggle("show");

        adminMenu.classList.remove("show");

        document.querySelector(".admin-arrow")
                .style.transform = "rotate(0deg)";
    }


    function toggleAdminMenu() {

        const adminMenu =
            document.getElementById("adminDropdown");

        const notification =
            document.getElementById("notificationDropdown");

        const arrow =
            document.querySelector(".admin-arrow");

        adminMenu.classList.toggle("show");

        notification.classList.remove("show");

        if (adminMenu.classList.contains("show")) {

            arrow.style.transform = "rotate(180deg)";

        } else {

            arrow.style.transform = "rotate(0deg)";
        }
    }


    document.addEventListener("click", function(event) {

        const notificationWrapper =
            document.querySelector(".notification-wrapper");

        const adminAccount =
            document.querySelector(".admin-account");

        const notificationDropdown =
            document.getElementById("notificationDropdown");

        const adminDropdown =
            document.getElementById("adminDropdown");

        const arrow =
            document.querySelector(".admin-arrow");


        if (!notificationWrapper.contains(event.target)) {

            notificationDropdown.classList.remove("show");
        }


        if (!adminAccount.contains(event.target)) {

            adminDropdown.classList.remove("show");

            arrow.style.transform = "rotate(0deg)";
        }

    });

</script>

</body>
</html>