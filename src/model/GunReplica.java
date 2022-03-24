package model;

import java.math.BigDecimal;

public class GunReplica extends Product {

	private double caliber;
	private String material;
	
	/**
	 * Constructor for the GunReplica class
	 * @param id the id of the gun replica
	 * @param name the name of the gun replica
	 * @param puchasePrice the purchase price of the gun replica
	 * @param salesPrice the sales price of the gun replica
	 * @param countryOfOrigin the country of origin of the gun replica
	 * @param minStock the minimum stock of the gun replica before reordering
	 * @param currentStock the current stock of the gun replica
	 * @param supplierPhoneno the supplier's phone number who supplies the gun replica
	 * @param calibre the caliber of the gun replica
	 * @param material the material of the gun replica
	 */
	public GunReplica(int id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock,  int currentStock, String supplierPhoneno, double caliber, String material) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock, currentStock, supplierPhoneno);
		this.caliber = caliber;
		this.material = material;
	}

	/**
	 * @return the caliber
	 */
	public double getCaliber() {
		return caliber;
	}

	/**
	 * @param calibre the caliber to set
	 */
	public void setCaliber(double caliber) {
		this.caliber = caliber;
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
