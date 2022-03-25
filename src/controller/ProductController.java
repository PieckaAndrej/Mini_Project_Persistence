package controller;
import dal.ProductDB;
import dal.ProductDBIF;
import exceptions.ProductNotFoundException;
import model.Product;


public class ProductController {
	private ProductDBIF productDb;

	/**
	 * Constructor for the ProductController class
	 */
	public ProductController() {
		productDb = new ProductDB();
	}
	
	/**
	 * Finds a product by its id in the database and returns it
	 * @param id the id of the product to search for
	 * @return the product with the corresponding id
	 * @throws ProductNotFoundException 
	 */
	public Product getProductById(int id) throws ProductNotFoundException {
		return productDb.getProductByID(id);
	}
}
