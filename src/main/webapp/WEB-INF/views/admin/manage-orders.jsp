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

    <title>BakerShop - Quản lý đơn hàng</title>

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

        <a href="<c:url value='/admin/manage-cakes'/>">

            <span>🍰</span>
            Manage Cakes

        </a>

        <a class="active"
           href="<c:url value='/admin/manage-orders'/>">

            <span>🛒</span>
            Manage Orders

        </a>


    </nav>

</aside>

<main class="main-content">

    <div class="page-header">

        <div>

            <h1>
                🛒 Quản lý đơn hàng
            </h1>

            <p>
                Quản lý và cập nhật trạng thái các đơn hàng.
            </p>

        </div>

    </div>

    <section class="dashboard-card">

        <div class="dashboard-card-header">

            <div>

                <h2>
                    📦 Danh sách đơn hàng
                </h2>

                <p>
                    Các đơn hàng mới nhất trong hệ thống
                </p>

            </div>

            <div class="order-count">

                ${orders.size()} đơn hàng

            </div>

        </div>

        <div class="table-wrapper">

            <table class="dashboard-table">

                <thead>

                <tr>

                    <th>MÃ ĐƠN</th>
                    <th>KHÁCH HÀNG</th>
                    <th>ĐỊA CHỈ</th>
                    <th>NGÀY GIAO</th>
                    <th>TỔNG TIỀN</th>
                    <th>THANH TOÁN</th>
                    <th>TRẠNG THÁI</th>

                </tr>

                </thead>

                <tbody>

                <c:forEach var="order"
                           items="${orders}">

                    <tr>

                        <td>

                            <strong>
                                #${order.id}
                            </strong>

                        </td>

                        <td>

                            <div class="dashboard-cake-name">

                                <div class="small-cake-icon">
                                    👤
                                </div>

                                Khách hàng #${order.userId}

                            </div>

                        </td>

                        <td>

                            <div class="order-address">

                                ${order.deliveryAddress}

                                <br>

                                <span>
                                    📞 ${order.deliveryPhone}
                                </span>

                            </div>

                        </td>

                        <td>

                            <c:if test="${not empty order.deliveryTime}">

                                <fmt:formatDate
                                        value="${order.deliveryTime}"
                                        pattern="dd/MM/yyyy HH:mm"/>

                            </c:if>

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

                                <c:when test="${order.paymentStatus == 'PAID'}">

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

                        <td>

                            <form method="post"
                                  action="<c:url value='/admin/manage-orders'/>"
                                  class="status-form">

                                <input type="hidden"
                                       name="id"
                                       value="${order.id}">

                                <select name="status"
                                        onchange="this.form.submit()">

                                    <option value="PENDING"
                                            ${order.status == 'PENDING' ? 'selected' : ''}>
                                        Chờ xử lý
                                    </option>

                                    <option value="CONFIRMED"
                                            ${order.status == 'CONFIRMED' ? 'selected' : ''}>
                                        Đã xác nhận
                                    </option>

                                    <option value="PREPARING"
                                            ${order.status == 'PREPARING' ? 'selected' : ''}>
                                        Đang chuẩn bị
                                    </option>

                                    <option value="DELIVERING"
                                            ${order.status == 'DELIVERING' ? 'selected' : ''}>
                                        Đang giao
                                    </option>

                                    <option value="COMPLETED"
                                            ${order.status == 'COMPLETED' ? 'selected' : ''}>
                                        Hoàn thành
                                    </option>

                                    <option value="CANCELLED"
                                            ${order.status == 'CANCELLED' ? 'selected' : ''}>
                                        Đã hủy
                                    </option>

                                </select>

                            </form>

                        </td>

                    </tr>

                </c:forEach>

                <c:if test="${empty orders}">

                    <tr>

                        <td colspan="7"
                            class="empty-data">

                            📦 Chưa có đơn hàng nào.

                        </td>

                    </tr>

                </c:if>

                </tbody>

            </table>

        </div>

    </section>

</main>

</body>

</html>