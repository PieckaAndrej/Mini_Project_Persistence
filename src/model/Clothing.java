package model;

import java.math.BigDecimal;

public class Clothing extends Product {

	private String size;
	private String colour;
	
	public Clothing(int id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock, String colour, String size) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock);
		this.size = size;
		this.colour = colour;
	}
}
