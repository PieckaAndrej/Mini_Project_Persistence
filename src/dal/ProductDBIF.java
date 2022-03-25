package dal;

import exceptions.ProductNotFoundException;
import model.Product;

public interface ProductDBIF {
	
	public Product getProductByID(int id) throws ProductNotFoundException;
	
}
