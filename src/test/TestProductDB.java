package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.DbConnection;
import dal.ProductDB;
import exceptions.DatabaseAccessException;
import model.Product;

public class TestProductDB {
	
	private ProductDB productDb;

	/*Test product data for testing:
	 * id: 1
	 * name: hat
	 * purchasePrice: 10
	 * salesPrice: 15
	 * countryOfOrigin: USA
	 * minStock: 2
	 * currentStock: 8
	 * supplierPhone: 1234567890
	 */
	
	// Test of getProductByID
	@BeforeEach
	public void setUp() {
		productDb = new ProductDB();
	}
	@Test
	public void ProductIDTest() {
		String command1 = "SELECT * FROM Product WHERE id = 1;";
		Product product = productDb.getProductByID(1);
		
		PreparedStatement ps;
		try {
			ps = DbConnection.getInstance().getConnection().prepareStatement(command1);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				assertEquals(product.getId(), rs.getInt("id"));
				assertEquals(product.getName(), rs.getString(2));
				assertEquals(product.getPuchasePrice(), rs.getBigDecimal(3));
				assertEquals(product.getSalesPrice(), rs.getBigDecimal(4));
				assertEquals(product.getCountryOfOrigin(), rs.getString(5));
				assertEquals(product.getMinStock(), rs.getInt(6));
				assertEquals(product.getCurrentStock(), rs.getInt(7));
				assertEquals(product.getSupplierPhoneno(), rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
