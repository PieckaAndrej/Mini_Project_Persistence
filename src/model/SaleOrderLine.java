package model;

public class SaleOrderLine extends OrderLine {
	private Product product;

	public SaleOrderLine(Product product, int quantity) {
		super(quantity);
		this.product = product;
	}

	@Override
	public double getAmount() {
		return product.getSalesPrice() * super.getQuantity();
	}

}
