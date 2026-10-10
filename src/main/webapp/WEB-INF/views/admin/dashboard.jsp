<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BakerShop - Admin Dashboard</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css" type="text/css">
    

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"></script>
</head>
<body class="dashboard-body">
    <nav class="admin-navbar">
        <div class="logo">Baker Admin</div>
        <ul>
            <li><a href="<c:url value='/admin/dashboard'/>">Dashboard</a></li>
            <li><a href="<c:url value='/admin/manage-cakes'/>">Manage Cakes</a></li>
            <li><a href="<c:url value='/admin/manage-orders'/>">Manage Orders</a></li>
        </ul>
    </nav>
    <div class="main">
        <h2 class="page-title">Tổng Quan Hệ Thống</h2>

        <!-- Thống kê nhanh (Cards) -->
        <div class="cards">
            <div class="card">
                <h3>Doanh thu ngày</h3>
                <p><c:out value="${revenue}" default="1.250.000 đ"/></p>
            </div>
            <div class="card">
                <h3>Đơn cần làm</h3>
                <p><c:out value="${pendingOrders}" default="8"/> đơn</p>
            </div>
            <div class="card" style="border-left-color: #ef4444;">
                <h3>Nguyên liệu sắp hết</h3>
                <p style="color:#ef4444;"><c:out value="${alertStock}" default="3 loại"/></p>
            </div>
        </div>

        <!-- Biểu đồ sản phẩm bán chạy -->
        <div class="charts">
            <div class="chart-box">
                <canvas id="salesChart"></canvas>
            </div>
        </div>
    </div>

    <!-- Script khởi tạo Biểu đồ Cột -->
    <script>
        const ctx = document.getElementById('salesChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Bánh Mì', 'Bánh Kem', 'Croissant', 'Tiramisu', 'Donut'],
                datasets: [{
                    label: 'Số lượng bán ra (Cái)',
                    data: [45, 12, 38, 25, 50],
                    backgroundColor: '#d4a373',
                    borderColor: '#bc8a5f',
                    borderWidth: 1,
                    borderRadius: 6
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    title: {
                        display: true,
                        text: 'Sản Phẩm Bán Chạy Trong Ngày',
                        font: { size: 16, weight: 'bold' },
                        color: '#1e293b'
                    },
                    legend: {
                        display: true,
                        position: 'bottom'
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: { stepSize: 10 }
                    }
                }
            }
        });
    </script>
</body>
</html>