package controller;
import dal.ProductDB;
import dal.ProductDBIF;
import model.Product;


public class ProductController {
	private ProductDBIF productDb;

	public ProductController() {
		productDb = new ProductDB();
	}
	
	public Product getProductById(String id) {
		return productDb.getProductByID(id);
	}
}
