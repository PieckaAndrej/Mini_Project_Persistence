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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPuchasePrice() {
		return puchasePrice;
	}

	public void setPuchasePrice(double puchasePrice) {
		this.puchasePrice = puchasePrice;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	
	
	
}
