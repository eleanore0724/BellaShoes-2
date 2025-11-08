package tw.com.lccnet.dao.daoLmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tw.com.lccnet.dao.OrderDao;
import tw.com.lccnet.databaseutils.DBUtils;
import tw.com.lccnet.model.CartItem;

public class OrderDaoImpl implements OrderDao{
	private Connection conn = DBUtils.getDataBase().getConnection();
	
	@Override
	public void createOrder(int user_id, List<CartItem> cartItems) {
		String sql = "INSERT INTO orders(user_id, product_id, quantity, created_at, status) VALUES (?, ?, ?, ?, '已成立')";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			
			for (CartItem item : cartItems) {
                ps.setInt(1, user_id);
                ps.setInt(2, item.getProduct_id());
                ps.setInt(3, item.getQuantity());
                ps.setTimestamp(4, Timestamp.valueOf(item.getCreated_at()));
                ps.addBatch();
            }
            ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void cancelOrder(int order_id) {
		String sql = "UPDATE orders SET status = '已取消' WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, order_id);
            ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//還沒寫完
	@Override
	public List<CartItem> getOrdersByUserId(int user_id) {
		String sql = "SELECT o.id, o.product_id, p.product_name, p.price, o.quantity, o.created_at, o.status FROM orders o JOIN product p ON o.product_id = p.product_id WHERE o.user_id = ? ORDER BY o.created_at DESC";
		List<CartItem> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
