package tw.com.lccnet.dao;

import java.util.List;

import tw.com.lccnet.model.CartItem;

public interface CartDao {
	
	void insertCartItem(int user_id, CartItem item);
	
	List<CartItem> getCartByUserId(int user_id);
	
	//取消訂單
	void deleteCartByUserId(int user_id);
}
