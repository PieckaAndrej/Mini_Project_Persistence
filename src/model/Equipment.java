package model;

import java.math.BigDecimal;

public class Equipment extends Product {

	private String type;
	private String description;
	
	/**
	 * Constructor for the Equipment class
	 * @param id the id of the equipment
	 * @param name the name of the equipment
	 * @param puchasePrice the purchase price of the equipment
	 * @param salesPrice the sales price of the equipment
	 * @param countryOfOrigin the country of origin of the equipment
	 * @param minStock the minimum stock of the equipment before reordering
	 * @param currentStock the current stock of the equipment
	 * @param supplierPhoneno the supplier's phone number who supplies the equipment
	 * @param type the type of the equipment
	 * @param description the description of the equipment
	 */
	public Equipment(int id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock,  int currentStock, String supplierPhoneno, String type, String description) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock, currentStock, supplierPhoneno);
		this.type = type;
		this.description = description;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
