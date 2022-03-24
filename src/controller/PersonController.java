package controller;

import dal.PersonDB;
import dal.PersonDBIF;
import exceptions.DatabaseAccessException;
import model.Person;

public class PersonController {
	private PersonDBIF personDb;
	
	/**
	 * Constructor for PersonController class
	 * @throws DatabaseAccessException 
	 */
	public PersonController() throws DatabaseAccessException {
		personDb = new PersonDB();
	}
	
	/**
	 * Searches for a person in the database and returns it
	 * @param phone the phone number of the person to search for
	 * @return the person with the corresponding phonenumber
	 */
	public Person getPersonByPhone(String phone) {
		return personDb.findByPhone(phone);
	}
}
