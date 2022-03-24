package model;

import java.math.BigDecimal;

public class Clothing extends Product {

	private String size;
	private String colour;
	
	public Clothing(int id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock,  int currentStock, String supplierPhoneno, String colour, String size) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock, currentStock, supplierPhoneno);
		this.size = size;
		this.colour = colour;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
}
