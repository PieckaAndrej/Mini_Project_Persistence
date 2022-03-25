package dal;

import java.util.List;

import model.Person;

public interface PersonDBIF {
	
	public Person findByPhone(String phone);
	
	public List<Person> getAllPerson();
	
}
