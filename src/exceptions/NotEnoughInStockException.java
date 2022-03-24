package exceptions;

public class NotEnoughInStockException extends Exception {
	private int expected;
	private int currently;
	
	public NotEnoughInStockException(int expected, int currently) {
		this.expected = expected;
		this.currently = currently;
	}
	
	public String getMessage() {
		return "There is not enough products in stock " + currently + "/" + expected;
	}
}
