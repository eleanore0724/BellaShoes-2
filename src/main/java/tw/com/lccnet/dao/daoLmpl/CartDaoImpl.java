package tw.com.lccnet.dao.daoLmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tw.com.lccnet.dao.CartDao;
import tw.com.lccnet.databaseutils.DBUtils;
import tw.com.lccnet.model.CartItem;

public class CartDaoImpl implements CartDao{
	
	private Connection conn = DBUtils.getDataBase().getConnection();

	@Override
	public void insertCartItem(int user_id, CartItem item) {
		String sql = "INSERT INTO cart(user_id, product_id, quantity, created_at) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, item.getProduct_id());
			ps.setInt(3, item.getQuantity());
			ps.setTimestamp(4, Timestamp.valueOf(item.getCreated_at()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}

	@Override
	public List<CartItem> getCartByUserId(int user_id) {
		String sql = "SELECT * FROM cart WHERE user_id = ?";
		List<CartItem> list = new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CartItem item = new CartItem();
				item.setCart_item_id( rs.getInt("product_id"));
				item.setProduct_name(rs.getString("product_name"));
				item.setPrice(rs.getInt("price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
				list.add(item);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void deleteCartByUserId(int user_id) {
		String sql = "DELETE FROM cart WHERE user_id = ?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
