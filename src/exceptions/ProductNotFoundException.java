package exceptions;

public class ProductNotFoundException extends Exception {
	private int id;
	
	public ProductNotFoundException(int id) {
		this.id = id;
	}
	
	public String getMessage() {
		return "No product with id " + id;
	}
}
