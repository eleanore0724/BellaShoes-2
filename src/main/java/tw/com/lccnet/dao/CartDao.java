package tw.com.lccnet.dao;

import java.util.List;

import tw.com.lccnet.model.CartItem;

public interface CartDao {
	
	public void addToCart(int productId);
	
	public List<CartItem> getAllCartItems();
	
	//有使用者的的取值
	public List<CartItem> getCartItemsByUser(int user_id);
	
	public int getCartCount();
	
	public int getCartTotalPrice();
}
