package tw.com.lccnet.model;

public class CartItem {
	private int cart_item_id;
	private int product_id;
	private int quantity;
	
	// 額外商品資訊欄位（JOIN 產品表用）
	private String product_name;
	private String image_url;
	private double price;
	
	public CartItem() {}
	
	public CartItem(int cart_item_id, int product_id, int quantity, String product_name, String image_url,
			double price) {
		super();
		this.cart_item_id = cart_item_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.product_name = product_name;
		this.image_url = image_url;
		this.price = price;
	}

	public int getCart_item_id() {
		return cart_item_id;
	}

	public void setCart_item_id(int cart_item_id) {
		this.cart_item_id = cart_item_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
