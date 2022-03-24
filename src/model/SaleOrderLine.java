package model;

import java.math.BigDecimal;

public class SaleOrderLine extends OrderLine {
	private Product product;

	/**
	 * Constructor for the SaleOrderLine class
	 * @param product the product of the order line
	 * @param quantity the quantity of the product
	 */
	public SaleOrderLine(Product product, int quantity) {
		super(quantity);
		this.product = product;
	}

	@Override
	public BigDecimal getAmount() {
		return product.getSalesPrice().multiply(new BigDecimal(super.getQuantity()));
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

}
