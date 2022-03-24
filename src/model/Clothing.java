package model;

import java.math.BigDecimal;

public class Clothing extends Product {

	private String size;
	private String color;
	
	/**
	 * Constructor for the Clothing class
	 * @param id the id of the clothing
	 * @param name the name of the clothing
	 * @param puchasePrice the purchase price of the clothing
	 * @param salesPrice the sales price of the clothing
	 * @param countryOfOrigin the country of origin of the clothing
	 * @param minStock the minimum stock of the clothing before reordering
	 * @param currentStock the current stock of the clothing
	 * @param supplierPhoneno the supplier's phone number who supplies the clothing
	 * @param color the color of the clothing
	 * @param size the size of the clothing
	 */
	public Clothing(int id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock,  int currentStock, String supplierPhoneno, String color, String size) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock, currentStock, supplierPhoneno);
		this.size = size;
		this.color = color;
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
}
