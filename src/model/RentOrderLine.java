package model;

import java.math.BigDecimal;

public class RentOrderLine extends OrderLine {
	
	private Copy copy;

	public RentOrderLine(int quantity, Copy copy) {
		super(quantity);
		this.copy = copy;
	}

	@Override
	public BigDecimal getAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the copy
	 */
	public Copy getCopy() {
		return copy;
	}

	/**
	 * @param copy the copy to set
	 */
	public void setCopy(Copy copy) {
		this.copy = copy;
	}

}
