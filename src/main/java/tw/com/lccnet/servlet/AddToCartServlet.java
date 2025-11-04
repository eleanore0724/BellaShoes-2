package tw.com.lccnet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.com.lccnet.dao.CartDao;
import tw.com.lccnet.dao.ProductDao;
import tw.com.lccnet.dao.daoLmpl.CartDaoImpl;
import tw.com.lccnet.dao.daoLmpl.ProductDaoImpl;
import tw.com.lccnet.model.CartItem;
import tw.com.lccnet.model.Product;
import tw.com.lccnet.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json;charset=UTF-8");
		 ObjectMapper mapper = new ObjectMapper();
		 
		//取得商品 ID
        String productIdStr = request.getParameter("product_id");
        int product_id = Integer.parseInt(productIdStr);
        System.out.println("取得ID為"+ product_id);
        Map<String, Object> result = new HashMap<>();
        try {
        CartDao dao = new CartDaoImpl();
        dao.addToCart(product_id);
       
        // 更新購物車數量放進 session
		int count = dao.getCartCount();
		HttpSession session =request.getSession();
		session.setAttribute("cartCount", count);
		
		// 回傳 JSON 給前端
		
		result.put("status", "success");
		result.put("cartCount", count);
		mapper.writeValue(response.getWriter(), result);
		System.out.println("目前購物車總數：" + count);
		
        }catch (Exception e) {
        	e.printStackTrace();
        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        	result.put("status", "error");
        	result.put("message", "加入購物車失敗");
        	mapper.writeValue(response.getWriter(), result);
        }
        request.getRequestDispatcher("products.jsp").forward(request, response);
	}
}