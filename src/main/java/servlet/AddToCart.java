package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.Cart;
import cart.CartHandler;
import product.Product;
import product.ProductHandler;

@WebServlet(urlPatterns = "/AddToCart")

public class AddToCart extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int productId = Integer.parseInt(req.getParameter("productId"));
		int userId = Integer.parseInt(req.getParameter("userId"));

		ProductHandler ph = new ProductHandler();

		Product p = ph.fetchProduct(Short.parseShort(req.getParameter("productId")));

		int currStock = p.getStockqty();
		int requestedStock = Integer.parseInt(req.getParameter("quantity"));

		CartHandler ch = new CartHandler();

		if (currStock < requestedStock) {
			resp.sendRedirect("NotEnoughStock.jsp");
		} else if (ch.checkCartIn(userId, productId)) {
			resp.sendRedirect("AlreadyInCart.jsp");

		} else {

			Cart c = new Cart(productId, userId, requestedStock);

			p.setStockqty((short) (currStock - requestedStock));
			ph.update(p);

			System.out.println(ch.insert(c));
			System.out.println(c);
			resp.sendRedirect("ViewCart.jsp");

		}

	}

}
