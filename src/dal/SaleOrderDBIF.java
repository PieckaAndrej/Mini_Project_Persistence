package dal;

import exceptions.DatabaseAccessException;
import model.SaleOrder;

public interface SaleOrderDBIF {
	
	public SaleOrder insertOrder(SaleOrder order) throws DatabaseAccessException;
	
}
