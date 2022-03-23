package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Product;

public class ProductDB implements ProductDBIF {

	public ProductDB() {

	}
	
	@Override
	public Product getProductByID(String id) {
		Product product = null;
		
		String sqlString = "SELECT Product WHERE id = ?";
		Connection con = DbConnection.getInstance().getConnection();

		try {
			PreparedStatement st = con.prepareStatement(sqlString);
			st.setString(1, id);
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
		return new Product(rs.getString("id"), rs.getString("name"), rs.getDouble("purchasePrice"), rs.getDouble("salesPrice"), rs.getString("countryOfOrigin"), rs.getInt("minStock"));
	}
}
