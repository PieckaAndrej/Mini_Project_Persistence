package model;

public class Product {
	
	private String id;
	private String name;
	private double puchasePrice;
	private double salesPrice;
	private String countryOfOrigin;
	private int minStock;
	
	public Product(String id, String name, double puchasePrice, double salesPrice, String countryOfOrigin,
			int minStock) {
		super();
		this.id = id;
		this.name = name;
		this.puchasePrice = puchasePrice;
		this.salesPrice = salesPrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}
	
	
}
