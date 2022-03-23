package database;

import model.Product;

public interface ProductDBIF {
	public Product getProductByID(String id);
}
