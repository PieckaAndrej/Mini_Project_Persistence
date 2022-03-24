package model;

import java.math.BigDecimal;

public abstract class OrderLine {
	private int quantity;
	
	public OrderLine(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Get amount of money that this order line costs
	 * @return Amount of money
	 */
	public abstract BigDecimal getAmount();

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
