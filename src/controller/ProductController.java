package controller;
import dal.ProductDB;
import dal.ProductDBIF;
import model.Product;


public class ProductController {
	private ProductDBIF productDatabase;

	public ProductController() {
		productDatabase = new ProductDB();
	}
	
	public Product getProductById(String id) {
		return productDatabase.getProductByID(id);
	}
}
