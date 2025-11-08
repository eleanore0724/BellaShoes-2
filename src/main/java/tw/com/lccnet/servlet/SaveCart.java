package tw.com.lccnet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.com.lccnet.dao.CartDao;
import tw.com.lccnet.dao.OrderDao;
import tw.com.lccnet.dao.daoLmpl.CartDaoImpl;
import tw.com.lccnet.dao.daoLmpl.OrderDaoImpl;
import tw.com.lccnet.model.CartItem;

import java.io.IOException;
import java.util.List;


@WebServlet("/saveCart")
public class SaveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		HttpSession session = request.getSession();
		User auth = (User) request.getSession().getAttribute("auth");
		
		if (auth == null) {
            response.sendRedirect("login.jsp");
            return;
        }
		//int user_id = auth.get;
		
		CartDao cartDao = new CartDaoImpl();
		OrderDao orderDao = new OrderDaoImpl();
		
		try {
			List<CartItem> cartItems = cartDao.getCartByUserId();
			
			// 下單：每次建立新訂單，不合併、不修改舊單
			orderDao.createOrder(user_id, cartItems);
			
			 // 清空購物車
            cartDao.deleteCartByUserId(user_id);
            
            response.sendRedirect("orderList");
			
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
