package com.bakershop.controller;

import com.bakershop.dao.OrderDAO;
import com.bakershop.dao.StoreDAO;
import com.bakershop.dao.VoucherDAO;
import com.bakershop.model.CartItem;
import com.bakershop.model.User;
import com.bakershop.model.Voucher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/checkout", "/checkout-success" })
public class CheckoutController extends HttpServlet {
	private static final String CART_ATTRIBUTE = "cartItems";
	private static final String VOUCHER_ATTRIBUTE = "checkoutVoucher";
	private final VoucherDAO voucherDAO = new VoucherDAO();
	private final StoreDAO storeDAO = new StoreDAO();
	private final OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("/checkout-success".equals(request.getServletPath())) {
			request.getRequestDispatcher("/WEB-INF/views/shop/checkout-success.jsp").forward(request, response);
			return;
		}
		User user = getUser(request.getSession(false));
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		List<CartItem> cartItems = getCart(request.getSession(true));
		if (cartItems.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		}
		double subtotal = getSubtotal(cartItems);
		Voucher voucher = (Voucher) request.getSession().getAttribute(VOUCHER_ATTRIBUTE);
		if (voucher != null && subtotal < voucher.getMinOrderAmount()) {
			request.getSession().removeAttribute(VOUCHER_ATTRIBUTE);
			voucher = null;
		}
		request.setAttribute("cartItems", cartItems);
		request.setAttribute("cartTotal", subtotal);
		request.setAttribute("voucher", voucher);
		double discount = getDiscount(voucher, subtotal);
		request.setAttribute("discountAmount", discount);
		request.setAttribute("finalTotal", subtotal - discount);
		request.setAttribute("storeList", storeDAO.getAllStores());
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/shop/checkout.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		User user = getUser(session);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		List<CartItem> cartItems = getCart(session);
		double subtotal = getSubtotal(cartItems);
		if (cartItems.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		}
		if ("placeOrder".equals(request.getParameter("action"))) {
			placeOrder(request, response, session, user, cartItems, subtotal);
			return;
		}
		String code = request.getParameter("voucherCode");
		if (code == null || code.trim().isEmpty()) {
			session.removeAttribute(VOUCHER_ATTRIBUTE);
			session.setAttribute("checkoutMessage", "Vui lòng nhập mã giảm giá.");
		} else {
			Voucher voucher = voucherDAO.findUsableByCode(code.trim(), subtotal);
			if (voucher == null) {
				session.removeAttribute(VOUCHER_ATTRIBUTE);
				session.setAttribute("checkoutMessage", "Mã giảm giá không hợp lệ hoặc không đủ điều kiện sử dụng.");
			} else {
				session.setAttribute(VOUCHER_ATTRIBUTE, voucher);
				session.setAttribute("checkoutMessage", "Đã áp dụng mã " + voucher.getCode() + ".");
			}
		}
		response.sendRedirect(request.getContextPath() + "/checkout");
	}

	private void placeOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			User user, List<CartItem> cartItems, double subtotal) throws IOException {
		int storeId = parsePositiveInt(request.getParameter("storeId"));
		String address = trim(request.getParameter("deliveryAddress"));
		String phone = trim(request.getParameter("deliveryPhone"));
		String paymentMethod = trim(request.getParameter("paymentMethod"));
		if (storeId <= 0 || address.isEmpty() || phone.isEmpty()
				|| !("momo".equals(paymentMethod) || "onlineBanking".equals(paymentMethod))) {
			session.setAttribute("checkoutMessage", "Vui lòng chọn phương thức thanh toán và nhập đầy đủ thông tin giao hàng.");
			response.sendRedirect(request.getContextPath() + "/checkout");
			return;
		}
		Voucher voucher = (Voucher) session.getAttribute(VOUCHER_ATTRIBUTE);
		double discount = getDiscount(voucher, subtotal);
		long orderId = orderDAO.createOrder(user.getId(), storeId, address, phone, cartItems,
				voucher, subtotal, discount, subtotal - discount);
		session.removeAttribute(CART_ATTRIBUTE);
		session.removeAttribute(VOUCHER_ATTRIBUTE);
		session.setAttribute("lastOrderId", orderId);
		response.sendRedirect(request.getContextPath() + "/checkout-success");
	}

	@SuppressWarnings("unchecked")
	private List<CartItem> getCart(HttpSession session) {
		Object value = session.getAttribute(CART_ATTRIBUTE);
		return value instanceof List<?> ? (List<CartItem>) value : new ArrayList<>();
	}

	private double getSubtotal(List<CartItem> cartItems) {
		return cartItems.stream().mapToDouble(item -> item.getCake().getPrice() * item.getQuantity()).sum();
	}

	private double getDiscount(Voucher voucher, double subtotal) {
		if (voucher == null) {
			return 0;
		}
		double discount = "PERCENTAGE".equalsIgnoreCase(voucher.getDiscountType())
				? subtotal * voucher.getDiscountValue() / 100 : voucher.getDiscountValue();
		if (voucher.getMaxDiscountAmount() != null) {
			discount = Math.min(discount, voucher.getMaxDiscountAmount());
		}
		return Math.max(0, Math.min(discount, subtotal));
	}

	private User getUser(HttpSession session) {
		return session == null ? null : (User) session.getAttribute("user");
	}

	private int parsePositiveInt(String value) {
		try {
			int number = Integer.parseInt(value);
			return number > 0 ? number : -1;
		} catch (NumberFormatException | NullPointerException exception) {
			return -1;
		}
	}

	private String trim(String value) {
		return value == null ? "" : value.trim();
	}
}
