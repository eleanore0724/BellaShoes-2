package tw.com.lccnet.dao.daoLmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.com.lccnet.dao.CartDao;
import tw.com.lccnet.databaseutils.DBUtils;
import tw.com.lccnet.model.CartItem;

public class CartDaoImpl implements CartDao{
	
	private Connection conn = DBUtils.getDataBase().getConnection();
	
	// 查詢購物車所有商品(有Auth)
	@Override
	public List<CartItem> getCartItemsByUser(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 查詢購物車所有商品(無Auth)
	@Override
	public List<CartItem> getAllCartItems() {
		List<CartItem> list = new ArrayList<>();
		String query = "SELECT c.cart_item_id, c.product_id, p.product_name, p.image_url, p.price, c.quantity FROM cart c JOIN products p ON c.product_id = p.product_id;";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CartItem item = new CartItem();
				item.setCart_item_id(rs.getInt("cart_item_id"));
				item.setProduct_id(rs.getInt("product_id"));
				item.setProduct_name(rs.getString("product_name"));
				item.setImage_url(rs.getString("image_url"));
				item.setPrice(rs.getInt("price"));
				item.setQuantity(rs.getInt("quantity"));
				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 查詢總數量
	@Override
	public int getCartCount() {
		String query = "SELECT SUM(quantity) AS total FROM cart";
		int total = 0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
	            total = rs.getInt("total");
	        }
		System.out.println("查詢數量總數"+total);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return total;
	}

	//新增或更新購物車商品
		@Override
		public void addToCart(int product_id) {
			try {
				String checkSQL = "SELECT * FROM cart WHERE product_id=?";
				PreparedStatement ps = conn.prepareStatement(checkSQL);
				ps.setInt(1, product_id);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					// 已存在 -> 數量 +1
					String update = "UPDATE cart SET quantity = quantity + 1 WHERE product_id=?";
					PreparedStatement psUpdate = conn.prepareStatement(update);
					psUpdate.setInt(1, product_id);
		            psUpdate.executeUpdate();
		            System.out.println("加入購物車");
				}else {
					// 新商品 -> 插入
					String insert = "INSERT INTO cart(product_id, quantity) VALUES (?, 1)";
					PreparedStatement psInsert = conn.prepareStatement(insert);
					psInsert.setInt(1, product_id);
					psInsert.executeUpdate();
					System.out.println("插入新的購物車");
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		//購物車的總金額
		@Override
		public int getCartTotalPrice() {
			// TODO Auto-generated method stub
			return 0;
		}


	

}
