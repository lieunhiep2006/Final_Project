<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Bánh</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css" type="text/css">
</head>
<body>
    <nav class="admin-navbar">
        <div class="logo">Baker Admin</div>
        <ul>
            <li><a href="<c:url value='/admin/dashboard'/>">Dashboard</a></li>
            <li><a href="<c:url value='/admin/manage-cakes'/>">Manage Cakes</a></li>
            <li><a href="<c:url value='/admin/manage-orders'/>">Manage Orders</a></li>
        </ul>
    </nav>

    <div class="container">
        
        <div class="page-header">
            <h2 class="page-title">Danh Sách Bánh Hiện Có</h2>
            <a href="#" class="btn btn-add">+ Thêm bánh mới</a>
        </div>

        <table class="cake-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Hình ảnh</th>
                    <th>Tên bánh</th>
                    <th>Giá tiền</th>
                    <th>Mô tả sản phẩm</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>

                <tr>
                    <td>1</td>
                    <td>
                        <img src="https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=100" class="img-thumb" alt="Bánh Chocolate">
                    </td>
                    <td><b>Bánh Kem Chocolate Truffle</b></td>
                    <td>350.000 đ</td>
                    <td>Bánh kem sô-cô-la đậm vị, đắng nhẹ kết hợp kem tươi.</td>
                    <td>
                        <a href="#" class="btn btn-edit">Sửa</a>
                        <a href="#" class="btn btn-delete">Xóa</a>
                    </td>
                </tr>


                <tr>
                    <td>2</td>
                    <td>
                        <img src="https://images.unsplash.com/photo-1565958011703-44f9829ba187?w=100" class="img-thumb" alt="Bánh Dâu">
                    </td>
                    <td><b>Bánh Shortcake Dâu Tây</b></td>
                    <td>280.000 đ</td>
                    <td>Sự kết hợp giữa dâu tây đà lạt tươi và lớp bông lan mềm.</td>
                    <td>
                        <a href="#" class="btn btn-edit">Sửa</a>
                        <a href="#" class="btn btn-delete">Xóa</a>
                    </td>
                </tr>


                <tr>
                    <td>3</td>
                    <td>
                        <img src="https://i.ytimg.com/vi/JiWRXq2fj_M/maxresdefault.jpg" class="img-thumb" alt="Bánh Matcha">
                    </td>
                    <td><b>Bánh Mousse Matcha Trà Xanh</b></td>
                    <td>310.000 đ</td>
                    <td>Mousse trà xanh Nhật Bản mát lạnh, vị ngọt thanh.</td>
                    <td>
                        <a href="#" class="btn btn-edit">Sửa</a>
                        <a href="#" class="btn btn-delete">Xóa</a>
                    </td>
                </tr>
            </tbody>
        </table>

    </div>
</body>
</html>