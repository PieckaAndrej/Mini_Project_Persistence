package dal;

import java.util.List;

import exceptions.DatabaseAccessException;
import model.Person;
import model.SaleOrder;

public interface SaleOrderDBIF {
	
	public SaleOrder insertOrder(SaleOrder order) throws DatabaseAccessException;
	
}
