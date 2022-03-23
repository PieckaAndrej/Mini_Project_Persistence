package controller;

import dal.PersonDB;
import dal.PersonDBIF;
import model.Person;

public class PersonController {
	private PersonDBIF personDb;
	
	public PersonController()
	{
		personDb = new PersonDB();
	}
	
	public Person getPersonByPhone(String phone)
	{
		return personDb.getPersonByPhone(phone);
	}
}
