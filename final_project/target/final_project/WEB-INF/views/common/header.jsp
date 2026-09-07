<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Meta Tags cơ bản -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- FontAwesome 6 (Dùng CDN để hiển thị các Icon) -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />

<!-- Google Fonts (Font chữ đẹp hợp với tiệm bánh) -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<!-- CSS Nội bộ (main.css) -->
<!-- Sử dụng ${pageContext.request.contextPath} để đường dẫn luôn đúng cho dù gọi ở bất kỳ Servlet nào -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css" type="text/css">