package dal;

import model.Person;

public interface PersonDBIF {
	
	public Person findByPhone(String phone);
	
}
