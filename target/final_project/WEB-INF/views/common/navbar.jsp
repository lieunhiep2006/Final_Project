<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="baker-navbar">
  <div class="baker-container">
    
    <!-- 1. Logo Tiệm Bánh -->
    <a class="baker-logo" href="${pageContext.request.contextPath}/home">
      <i class="fa-solid fa-cake-candles"></i>
      <span>BakerShop</span>
    </a>

    <!-- Menu Điều Hướng -->
    <ul class="baker-menu">
      <!-- Trang Chủ -->
      <li>
        <a class="active" href="${pageContext.request.contextPath}/home">Trang chủ</a>
      </li>

        <!-- Giới Thiệu -->
      <li>
        <a href="${pageContext.request.contextPath}/about">Giới thiệu</a>
      </li>

      <!-- 2. Menu Danh Mục Bánh (Dropdown CSS) -->
      <li class="dropdown">
        <a href="#">Thực đơn bánh <i class="fa-solid fa-chevron-down" style="font-size: 0.8rem;"></i></a>
        <ul class="dropdown-menu">
          <li><a href="${pageContext.request.contextPath}/products">Tất cả bánh</a></li>
          
          <c:forEach var="cat" items="${categoryList}">
            <li>
              <a href="${pageContext.request.contextPath}/products?categoryId=${cat.id}">
                ${cat.name}
              </a>
            </li>
          </c:forEach>
        </ul>
      </li>

      <!-- 3. Địa Chỉ Cơ Sở -->
      <li>
        <a href="${pageContext.request.contextPath}/stores">
          <i class="fa-solid fa-location-dot" style="color: var(--primary-color);"></i> Cửa hàng
        </a>
      </li>
    </ul>

    <!-- 4. Ô Tìm Kiếm -->
    <form class="search-form" action="${pageContext.request.contextPath}/products" method="GET">
      <div class="search-group">
        <input class="search-input" type="search" name="query" placeholder="Tìm loại bánh..." value="${param.query}">
        <button class="search-btn" type="submit">
          <i class="fa-solid fa-magnifying-glass"></i>
        </button>
      </div>
    </form>

    <!-- Khối Tài Khoản & Giỏ Hàng -->
    <div class="baker-actions">
      
      <!-- 5. Ô Đăng Nhập / Tài Khoản -->
      <c:choose>
        <%-- Chưa đăng nhập --%>
        <c:when test="${empty sessionScope.user}">
          <a href="${pageContext.request.contextPath}/login" class="btn-auth">
            <i class="fa-solid fa-right-to-bracket"></i> Đăng nhập
          </a>
        </c:when>
        
        <%-- Đã đăng nhập --%>
        <c:otherwise>
          <div class="dropdown">
            <a href="#" class="btn-auth">
              <i class="fa-solid fa-user"></i> ${sessionScope.user.fullName}
            </a>
            <ul class="dropdown-menu">
              <li><a href="${pageContext.request.contextPath}/profile">Thông tin tài khoản</a></li>
              <li><a href="${pageContext.request.contextPath}/orders">Đơn hàng của tôi</a></li>
              <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <li><a href="${pageContext.request.contextPath}/admin/dashboard" style="color: var(--hover-color);">Trang Quản Trị</a></li>
              </c:if>
              <li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
            </ul>
          </div>
        </c:otherwise>
      </c:choose>

      <!-- 6. Giỏ Hàng -->
      <a href="${pageContext.request.contextPath}/cart" class="btn-cart">
        <i class="fa-solid fa-cart-shopping"></i>
        <span class="cart-badge">
          ${sessionScope.cartCount != null ? sessionScope.cartCount : 0}
        </span>
      </a>

    </div>
  </div>
</nav>