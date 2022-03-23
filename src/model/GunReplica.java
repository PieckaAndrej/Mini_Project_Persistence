package model;

public class GunReplica extends Product {

	private String calibre;
	private String material;
	
	public GunReplica(String id, String name, double puchasePrice, double salesPrice, String countryOfOrigin,
			int minStock, String calible, String material) {
		super(id, name, puchasePrice, salesPrice, countryOfOrigin, minStock);
		this.calibre = calible;
		this.material = material;
	}

}
