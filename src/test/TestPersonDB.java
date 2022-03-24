package test;

import static org.junit.Assert.assertEquals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.DbConnection;
import dal.PersonDB;
import exceptions.DatabaseAccessException;
import model.Person;
import model.SaleOrder;

public class TestPersonDB {
	
	private PersonDB personDB;
	
	@BeforeEach
	void setUp() {
		personDB = new PersonDB();
	}
	
	@Test
	void testGetPersonByPhone()
	{
		String getQuery = "SELECT * FROM Person WHERE phoneno = 1234567890;";
		
		String getQuery1 = "SELECT city FROM Country WHERE country = 'Denmark' and zipcode = '9000';";
		
		Person person = personDB.getPersonByPhone("1234567890");
		
		PreparedStatement prst;
		
		PreparedStatement prst1;
		
		try {
			prst = DbConnection.getInstance().getConnection().prepareStatement(getQuery);
			
			prst1 = DbConnection.getInstance().getConnection().prepareStatement(getQuery1);
			
			ResultSet rs = prst.executeQuery();
			
			ResultSet rs1 = prst1.executeQuery();
			
			
			if (rs.next()) {
				assertEquals(person.getName(), rs.getString("name"));
				assertEquals(person.getAddress(), rs.getString("address"));
				assertEquals(person.getCountry(), rs.getString("country"));
				assertEquals(person.getZipcode(), rs.getString("zipcode"));
				assertEquals(person.getPhoneno(), rs.getString("phoneno"));
				assertEquals(person.getEmail(), rs.getString("email"));
				
			}
			
			if(rs1.next())
			{
				assertEquals(person.getCity(), rs1.getString("city"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
