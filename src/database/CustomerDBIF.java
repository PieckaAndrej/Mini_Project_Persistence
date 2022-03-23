package database;

import model.Customer;

public interface CustomerDBIF {

	public Customer insert(Customer c);
	public boolean delete(Customer c);
	public Customer update(Customer c);
	public Customer getCustomerByPhone(String phone, boolean fullAssociation);
}
