package tw.com.lccnet.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.lccnet.dao.CartDao;
import tw.com.lccnet.dao.daoLmpl.CartDaoImpl;
import tw.com.lccnet.model.CartItem;

import java.io.IOException;
import java.util.List;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CartDao dao = new CartDaoImpl();
			List<CartItem> cartList = dao.getAllCartItems();
			System.out.println(cartList);
			request.setAttribute("cartList", cartList);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("查詢購物車時發生錯誤：" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
