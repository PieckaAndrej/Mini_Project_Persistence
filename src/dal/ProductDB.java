package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class ProductDB implements ProductDBIF {

	public ProductDB() {

	}
	
	@Override
	public Product getProductByID(int id) {
		Product product = null;
		
		String sqlString = "SELECT * FROM Product WHERE id = ?";
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
		return new Product(rs.getInt("id"), rs.getString("name"),
				rs.getBigDecimal("purchasePrice"), rs.getBigDecimal("salesPrice"),
				rs.getString("countryOfOrigin"), rs.getInt("minStock"), 
				rs.getInt("currentStock"), rs.getString("supplierPhoneno"));
	}
}
