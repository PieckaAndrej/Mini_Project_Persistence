package dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Clothing;
import model.Equipment;
import model.GunReplica;
import model.Product;

public class ProductDB implements ProductDBIF {

	public ProductDB() {

	}
	
	@Override
	public Product getProductByID(int id) {
		Product product = null;
		
		String sqlString = "select distinct *, GunReplica.productId as id1, Clothing.productId as id2,"
				+ " Equipment.productId as id3 from Product left join GunReplica on Product.id = GunReplica.productId"
				+ " left join Clothing on Product.id = Clothing.productId left join Equipment on Product.id ="
				+ " Equipment.productId where Product.id = ?;";
		Connection con = DbConnection.getInstance().getConnection();

		try {
			PreparedStatement st = con.prepareStatement(sqlString);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				product = buildObject(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private Product buildObject(ResultSet rs) throws SQLException {
		Product product;
		int id = rs.getInt("id");
		String name = rs.getString("name");
		BigDecimal purchasePrice = rs.getBigDecimal("purchasePrice");
		BigDecimal salesPrice = rs.getBigDecimal("salesPrice");
		String countryOfOrigin = rs.getString("countryOfOrigin");
		int minStock = rs.getInt("minStock"); 
		int currentStock = rs.getInt("currentStock");
		String supplierPhoneno = rs.getString("supplierPhoneno");
		
		if(rs.getInt("id1") != 0) {
			product = new GunReplica(id, name, purchasePrice, salesPrice, countryOfOrigin, minStock, 
					currentStock, supplierPhoneno, rs.getDouble("calibre"), rs.getString("material"));
		} else if(rs.getInt("id2") != 0) {
			product = new Clothing(id, name, purchasePrice, salesPrice, countryOfOrigin, minStock,
					currentStock, supplierPhoneno, rs.getString("color"), rs.getString("size"));
		} else if(rs.getInt("id3") != 0) {
			product = new Equipment(id, name, purchasePrice, salesPrice, countryOfOrigin, minStock,
					currentStock, supplierPhoneno, rs.getString("type"), rs.getString("description"));
		} else {
			product = new Product(id, name, purchasePrice, salesPrice, countryOfOrigin, minStock, currentStock,
					supplierPhoneno);
		}
		return product;
	}
}
