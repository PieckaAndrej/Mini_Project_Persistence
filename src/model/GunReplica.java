package model;

import java.math.BigDecimal;

public class GunReplica extends Product {

	private String calibre;
	private String material;
	
	public GunReplica(String id, String name, BigDecimal puchasePrice, BigDecimal salesPrice, String countryOfOrigin,
			int minStock, String calible, String material) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock);
		this.calibre = calible;
		this.material = material;
	}

}
