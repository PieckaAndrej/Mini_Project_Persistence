package model;

import java.math.BigDecimal;

public class SaleOrderLine extends OrderLine {
	private Product product;

	public SaleOrderLine(Product product, int quantity) {
		super(quantity);
		this.product = product;
	}

	@Override
	public BigDecimal getAmount() {
		return product.getSalesPrice().multiply(new BigDecimal(super.getQuantity()));
	}

}
