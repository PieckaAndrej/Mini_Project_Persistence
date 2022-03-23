package controller;
import database.ProductDb;
import model.Product;


public class ProductController {

	public ProductController() {
		
		public Product createProduct(String id, String name, double salesPrice, double rentPrice, String countryOfOrigin, int minStock) {
			
		}
		public Product getProductById(String id) {
			return ProductDb.getInstance().getProduct(id);
		}
	}
}
