package model;

public class SaleOrderLine extends OrderLine {
	private Product product;

	public SaleOrderLine(Product product, int quantity) {
		super(quantity);
	}

	@Override
	public double getAmount() {
		return product.getSalesPrice();
	}

}
