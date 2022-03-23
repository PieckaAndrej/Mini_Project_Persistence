package model;

import java.math.BigDecimal;

public abstract class OrderLine {
	private int quantity;
	
	public OrderLine(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Get amount of money that this orderline costs
	 * @return Amount of money
	 */
	public abstract BigDecimal getAmount();
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
