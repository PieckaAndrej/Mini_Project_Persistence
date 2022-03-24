package model;

import java.math.BigDecimal;

public class Equipment extends Product {

	private String type;
	private String description;
	
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
