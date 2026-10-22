<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>BakerShop - Quản lý Voucher</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
        <style>
            .btn-add { background: #e0663b; color: white; padding: 10px 16px; border-radius: 8px; text-decoration: none; font-weight: bold; border: none; cursor: pointer; }
            .btn-delete { color: #d9534f; text-decoration: none; font-weight: bold; }
            .modal { display: none; position: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); }
            .modal-content { background: white; margin: 5% auto; padding: 24px; border-radius: 12px; width: 450px; }
            .form-group { margin-bottom: 12px; }
            .form-group label { display: block; margin-bottom: 4px; font-weight: 500; }
            .form-group input, .form-group select { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 6px; box-sizing: border-box; }
            .modal-actions { display: flex; justify-content: flex-end; gap: 10px; margin-top: 16px; }
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
            <div class="page-header" style="display: flex; justify-content: space-between; align-items: center;">
                <div>
                    <h1>🎟️ Quản lý Voucher</h1>
                    <p>Danh sách các mã giảm giá và ưu đãi trong hệ thống.</p>
                </div>
                <button class="btn-add" onclick="openModal()">+ Thêm Voucher Mới</button>
            </div>

            <section class="dashboard-card">
                <div class="dashboard-card-header">
                    <div>
                        <h2>Danh sách Voucher</h2>
                        <p>Quản lý các mã khuyến mãi</p>
                    </div>
                </div>
                <div class="table-wrapper">
                    <table class="dashboard-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Mã Code</th>
                                <th>Loại giảm</th>
                                <th>Giá trị</th>
                                <th>Đơn tối thiểu</th>
                                <th>Giảm tối đa</th>
                                <th>Giới hạn dùng</th>
                                <th>Hạn sử dụng</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="v" items="${vouchers}">
                                <tr>
                                    <td>#${v.id}</td>
                                    <td><strong>${v.code}</strong></td>
                                    <td>${v.discountType}</td>
                                    <td class="dashboard-price">
                                        <fmt:formatNumber value="${v.discountValue}" type="number" groupingUsed="true"/>
                                        <c:if test="${v.discountType == 'PERCENT'}">%</c:if>
                                        <c:if test="${v.discountType != 'PERCENT'}">đ</c:if>
                                    </td>
                                    <td><fmt:formatNumber value="${v.minOrderAmount}" type="number" groupingUsed="true"/> đ</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${v.maxDiscountAmount != null}">
                                                <fmt:formatNumber value="${v.maxDiscountAmount}" type="number" groupingUsed="true"/> đ
                                            </c:when>
                                            <c:otherwise>—</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${v.usageLimit != null ? v.usageLimit : 'Vô hạn'}</td>
                                    <td>${v.startDate} đến ${v.endDate}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/manage-vouchers/delete?id=${v.id}" 
                                           class="btn-delete" onclick="return confirm('Bạn có chắc chắn muốn xóa voucher này?');">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty vouchers}">
                                <tr>
                                    <td colspan="9" style="text-align: center;">Chưa có mã voucher nào trong hệ thống.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>

        <!-- Modal Thêm Voucher -->
        <div id="voucherModal" class="modal">
            <div class="modal-content">
                <h2>Thêm Voucher Mới</h2>
                <form action="${pageContext.request.contextPath}/admin/manage-vouchers/add" method="post">
                    <div class="form-group">
                        <label>Mã Code</label>
                        <input type="text" name="code" required placeholder="Ví dụ: KM2026">
                    </div>
                    <div class="form-group">
                        <label>Loại giảm giá</label>
                        <select name="discountType">
                            <option value="FIXED">Cố định (đ)</option>
                            <option value="PERCENT">Phần trăm (%)</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Giá trị giảm</label>
                        <input type="number" step="0.01" name="discountValue" required placeholder="Ví dụ: 30000 hoặc 10">
                    </div>
                    <div class="form-group">
                        <label>Đơn tối thiểu (đ)</label>
                        <input type="number" step="0.01" name="minOrderAmount" value="0">
                    </div>
                    <div class="form-group">
                        <label>Giảm tối đa (đ)</label>
                        <input type="number" step="0.01" name="maxDiscountAmount" placeholder="Bỏ trống nếu không giới hạn">
                    </div>
                    <div class="form-group">
                        <label>Số lượng giới hạn</label>
                        <input type="number" name="usageLimit" placeholder="Bỏ trống nếu không giới hạn">
                    </div>
                    <div class="form-group">
                        <label>Ngày bắt đầu</label>
                        <input type="date" name="startDate" required>
                    </div>
                    <div class="form-group">
                        <label>Ngày kết thúc</label>
                        <input type="date" name="endDate" required>
                    </div>
                    <div class="modal-actions">
                        <button type="button" onclick="closeModal()">Hủy</button>
                        <button type="submit" class="btn-add">Thêm</button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            function openModal() { document.getElementById('voucherModal').style.display = 'block'; }
            function closeModal() { document.getElementById('voucherModal').style.display = 'none'; }
        </script>
    </body>
</html>