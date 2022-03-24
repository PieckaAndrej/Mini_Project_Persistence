package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Person;

public class PersonDB implements PersonDBIF {
	
	@Override
	public Person insert(Person c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Person c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person update(Person c) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Person getPersonByPhone(String phone) {
		
		Person person = null;

		String sqlString = "SELECT * FROM Person WHERE phoneno = ?";
		String sqlFindCity = "SELECT City FROM Country WHERE country = ? and zipcode = ?";
		Connection connection = DbConnection.getInstance().getConnection();

		try {
			PreparedStatement st  = connection.prepareStatement(sqlString);
			PreparedStatement stFindCity  = connection.prepareStatement(sqlFindCity);
			st.setString(1, phone);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				stFindCity.setString(1, rs.getString("country"));
				stFindCity.setString(2, rs.getString("zipcode"));
				ResultSet rsCity = stFindCity.executeQuery();
				if(rsCity.next()) {
					person = buildObject(rs, rsCity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
	
	private Person buildObject(ResultSet rs, ResultSet rsCity) throws SQLException {
		return new Person(rs.getString("name"), rs.getString("address"),
				rsCity.getString("city"), rs.getString("phoneno"),
				rs.getString("zipcode"), rs.getString("country"), rs.getString("email"));
	}

}
