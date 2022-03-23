package dal;

import model.Person;

public interface PersonDBIF {

	public Person insert(Person c);
	public boolean delete(Person c);
	public Person update(Person c);
	public Person getCustomerByPhone(String phone);
}
