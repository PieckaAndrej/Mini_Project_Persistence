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
		Connection connection = DbConnection.getInstance().getConnection();

		try {
			PreparedStatement st  = connection.prepareStatement(sqlString);
			st.setString(1, phone);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				person = buildObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
	
	private Person buildObject(ResultSet rs) throws SQLException {
		return new Person(rs.getString("name"), rs.getString("address"),
				rs.getString("country"), rs.getString("zipcode"),
				rs.getString("phoneno"), rs.getString("email"));
	}

}
