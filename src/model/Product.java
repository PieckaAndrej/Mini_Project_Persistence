package model;

import java.math.BigDecimal;

public class Product {
	
	private int id;
	private String name;
	private BigDecimal puchasePrice;
	private BigDecimal salesPrice;
	private String countryOfOrigin;
	private int minStock;
	private int currentStock;
	private String supplierPhoneno;
	
	/**
	 * Constructor for the Product class
	 * @param id the id of the product
	 * @param name the name of the product
	 * @param puchasePrice the purchase price of the product
	 * @param salesPrice the sales price of the product
	 * @param countryOfOrigin the country of origin of the product
	 * @param minStock the minimum stock of the product before reordering
	 * @param currentStock the current stock of the product
	 * @param supplierPhoneno the supplier's phone number who supplies the product
	 */
	public Product(int id, String name, BigDecimal puchasePrice,
			BigDecimal salesPrice, String countryOfOrigin, int minStock, int currentStock, String supplierPhoneno) {
		this.id = id;
		this.name = name;
		this.puchasePrice = puchasePrice;
		this.salesPrice = salesPrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.currentStock = currentStock;
		this.supplierPhoneno = supplierPhoneno;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the puchasePrice
	 */
	public BigDecimal getPuchasePrice() {
		return puchasePrice;
	}

	/**
	 * @param puchasePrice the puchasePrice to set
	 */
	public void setPuchasePrice(BigDecimal puchasePrice) {
		this.puchasePrice = puchasePrice;
	}

	/**
	 * @return the salesPrice
	 */
	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice the salesPrice to set
	 */
	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * @return the countryOfOrigin
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	/**
	 * @param countryOfOrigin the countryOfOrigin to set
	 */
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	/**
	 * @return the minStock
	 */
	public int getMinStock() {
		return minStock;
	}

	/**
	 * @param minStock the minStock to set
	 */
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	/**
	 * @return the currentStock
	 */
	public int getCurrentStock() {
		return currentStock;
	}

	/**
	 * @param currentStock the currentStock to set
	 */
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	/**
	 * @return the supplierPhoneno
	 */
	public String getSupplierPhoneno() {
		return supplierPhoneno;
	}

	/**
	 * @param supplierPhoneno the supplierPhoneno to set
	 */
	public void setSupplierPhoneno(String supplierPhoneno) {
		this.supplierPhoneno = supplierPhoneno;
	}
	
	
	
}
