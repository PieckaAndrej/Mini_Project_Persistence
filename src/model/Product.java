package model;

import java.math.BigDecimal;

public class Product {
	
	private int id;
	private String name;
	private BigDecimal puchasePrice;
	private BigDecimal salesPrice;
	private String countryOfOrigin;
	private int minStock;
	
	public Product(int id, String name, BigDecimal puchasePrice,
			BigDecimal salesPrice, String countryOfOrigin, int minStock) {
		this.id = id;
		this.name = name;
		this.puchasePrice = puchasePrice;
		this.salesPrice = salesPrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPuchasePrice() {
		return puchasePrice;
	}

	public void setPuchasePrice(BigDecimal puchasePrice) {
		this.puchasePrice = puchasePrice;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
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
