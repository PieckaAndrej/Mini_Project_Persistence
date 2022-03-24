package model;

import java.math.BigDecimal;

public class GunReplica extends Product {

	private double calibre;
	private String material;
	
	public GunReplica(int id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock,  int currentStock, String supplierPhoneno, double calible, String material) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock, currentStock, supplierPhoneno);
		this.calibre = calible;
		this.material = material;
	}

	/**
	 * @return the calibre
	 */
	public double getCalibre() {
		return calibre;
	}

	/**
	 * @param calibre the calibre to set
	 */
	public void setCalibre(double calibre) {
		this.calibre = calibre;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

}
