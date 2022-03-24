package model;

import java.math.BigDecimal;

public class Equipment extends Product {

	private String type;
	private String description;
	
	public Equipment(int id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock, String type, String description) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock);
		this.type = type;
		this.description = description;
	}

}
