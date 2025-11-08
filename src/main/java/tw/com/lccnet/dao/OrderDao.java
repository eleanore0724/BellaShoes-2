package tw.com.lccnet.dao;

import java.util.List;

import tw.com.lccnet.model.CartItem;

public interface OrderDao {
	
	void createOrder(int user_id, List<CartItem> cartItems);
	
	void cancelOrder(int order_id);
	
	List<CartItem> getOrdersByUserId(int user_id);
}
