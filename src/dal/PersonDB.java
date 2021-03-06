package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DatabaseAccessException;
import model.Person;

public class PersonDB implements PersonDBIF {
	
	private static final String SELECT_PERSON_STATEMENT = "SELECT * FROM Person WHERE phoneno = ?";
	private PreparedStatement selectPersonStatement;
	private static final String SELECT_CITY_STATEMENT = "SELECT City FROM Country WHERE country = ? and zipcode = ?";
	private PreparedStatement selectCityStatement;
	private static final String SELECT_ALL_PERSON_STATEMENT = "SELECT * FROM Person";
	private PreparedStatement selectAllPersonStatement;
	
	/**
	 * Constructor for PersonDB class
	 * @throws DatabaseAccessException 
	 */
	public PersonDB() throws DatabaseAccessException {
		try {
			selectPersonStatement = DbConnection.getInstance().getConnection().prepareStatement(SELECT_PERSON_STATEMENT);
			selectCityStatement = DbConnection.getInstance().getConnection().prepareStatement(SELECT_CITY_STATEMENT);
			selectAllPersonStatement = DbConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_PERSON_STATEMENT);
		} catch (SQLException e) {
			throw new DatabaseAccessException(DatabaseAccessException.CONNECTION_MESSAGE);
		}
	}

	/**
	 * Searches for a person in the database and returns it
	 * @param phone the phone number of the person to search for
	 * @return the person with the corresponding phone number
	 */
	@Override
	public Person findByPhone(String phone) {

		Person person = null;

		try {
			selectPersonStatement.setString(1, phone);
			ResultSet rs = selectPersonStatement.executeQuery();
			if (rs.next()) {
				selectCityStatement.setString(1, rs.getString("country"));
				selectCityStatement.setString(2, rs.getString("zipcode"));
				ResultSet rsCity = selectCityStatement.executeQuery();
				if(rsCity.next()) {
					person = buildObject(rs, rsCity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
	
	@Override
	public List<Person> getAllPerson() {
		List<Person> ps = new ArrayList<>();
		
		try {
			ResultSet rs = selectAllPersonStatement.executeQuery();
			ps = buildObjects(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * Creates a Person object from a result set
	 * @param rs the result set containing the data from the person table
	 * @param rsCity the result set which contains the city
	 * @return the person created from result sets
	 * @throws SQLException
	 */
	private Person buildObject(ResultSet rs, ResultSet rsCity) throws SQLException {
		return new Person(rs.getString("name"), rs.getString("address"),
				rsCity.getString("city"), rs.getString("phoneno"),
				rs.getString("zipcode"), rs.getString("country"), rs.getString("email"));
	}
	
	private List<Person> buildObjects(ResultSet rs) throws SQLException {
		List<Person> ps = new ArrayList<>();
		
		while(rs.next()) {
			selectCityStatement.setString(1, rs.getString("country"));
			selectCityStatement.setString(2, rs.getString("zipcode"));
			ResultSet rsCity = selectCityStatement.executeQuery();
			if(rsCity.next()) {
				ps.add(buildObject(rs, rsCity));
			}
		}
		
		return ps;
	}


}
