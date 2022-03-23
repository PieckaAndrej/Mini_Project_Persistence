package model;

public class Clothing extends Product {

	private String size;
	private String colour;
	
	public Clothing(String id, String name, double puchasePrice, double salesPrice, String countryOfOrigin,
			int minStock, String colour, String size) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock);
		this.size = size;
		this.colour = colour;
	}
}
