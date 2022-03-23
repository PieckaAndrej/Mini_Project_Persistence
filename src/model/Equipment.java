package model;

public class Equipment extends Product {

	private String type;
	private String description;
	
	public Equipment(String id, String name, double puchasePrice, double salesPrice, String countryOfOrigin,
			int minStock, String type, String description) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock);
		this.type = type;
		this.description = description;
	}

}
