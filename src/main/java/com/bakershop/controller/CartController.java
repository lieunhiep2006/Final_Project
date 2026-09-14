package com.bakershop.controller;

import com.bakershop.dao.CakeDAO;
import com.bakershop.model.CartItem;
import com.bakershop.model.Cake;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final String CART_ATTRIBUTE = "cartItems";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CartItem> cartItems = getCart(request.getSession(true));
		updateCartCount(request.getSession());
		request.setAttribute("cartItems", cartItems);
		request.setAttribute("cartTotal", getCartTotal(cartItems));
		request.getRequestDispatcher("/WEB-INF/views/shop/cart.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("remove".equals(action)) {
			removeItem(request);
		} else if ("update".equals(action)) {
			updateItem(request);
		} else if ("clear".equals(action)) {
			getCart(request.getSession(true)).clear();
			updateCartCount(request.getSession());
		} else {
			addItem(request);
		}
		response.sendRedirect(request.getContextPath() + "/cart");
	}

	@SuppressWarnings("unchecked")
	private List<CartItem> getCart(HttpSession session) {
		Object value = session.getAttribute(CART_ATTRIBUTE);
		if (value instanceof List<?>) {
			return (List<CartItem>) value;
		}
		List<CartItem> cart = new ArrayList<>();
		session.setAttribute(CART_ATTRIBUTE, cart);
		return cart;
	}

	private void addItem(HttpServletRequest request) throws ServletException {
		int cakeId = parsePositiveInt(request.getParameter("cakeId"));
		int quantity = parsePositiveInt(request.getParameter("quantity"));
		Cake cake = new CakeDAO().findById(cakeId);
		if (cake == null || cake.getStockQuantity() < quantity) {
			request.getSession(true).setAttribute("cartError", "Sản phẩm không tồn tại hoặc không đủ số lượng.");
			return;
		}
		List<CartItem> cart = getCart(request.getSession(true));
		for (CartItem item : cart) {
			if (item.getCake().getId() == cakeId) {
				item.setQuantity(Math.min(item.getQuantity() + quantity, cake.getStockQuantity()));
				updateCartCount(request.getSession());
				return;
			}
		}
		cart.add(new CartItem(cake, quantity));
		updateCartCount(request.getSession());
	}

	private void updateItem(HttpServletRequest request) throws ServletException {
		int cakeId = parsePositiveInt(request.getParameter("cakeId"));
		int quantity = parsePositiveInt(request.getParameter("quantity"));
		Cake cake = new CakeDAO().findById(cakeId);
		List<CartItem> cart = getCart(request.getSession(true));
		for (CartItem item : cart) {
			if (item.getCake().getId() == cakeId) {
				if (cake == null || cake.getStockQuantity() < quantity) {
					request.getSession().setAttribute("cartError", "Số lượng sản phẩm không đủ trong kho.");
					return;
				}
				item.setCake(cake);
				item.setQuantity(quantity);
				updateCartCount(request.getSession());
				return;
			}
		}
		request.getSession().setAttribute("cartError", "Sản phẩm không có trong giỏ hàng.");
	}

	private void removeItem(HttpServletRequest request) throws ServletException {
		int cakeId = parsePositiveInt(request.getParameter("cakeId"));
		getCart(request.getSession(true)).removeIf(item -> item.getCake().getId() == cakeId);
		updateCartCount(request.getSession());
	}

	private int parsePositiveInt(String value) throws ServletException {
		try {
			int number = Integer.parseInt(value);
			if (number > 0) {
				return number;
			}
		} catch (NumberFormatException | NullPointerException ignored) { }
		throw new ServletException("Invalid cart value");
	}

	private double getCartTotal(List<CartItem> cart) {
		return cart.stream().mapToDouble(item -> item.getCake().getPrice() * item.getQuantity()).sum();
	}

	private void updateCartCount(HttpSession session) {
		int count = getCart(session).stream().mapToInt(CartItem::getQuantity).sum();
		session.setAttribute("cartCount", count);
	}
}
