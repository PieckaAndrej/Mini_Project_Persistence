package dal;

import exceptions.DatabaseAccessException;
import model.OrderLine;

public interface OrderLineDBIF {

	public void insertOrderLine(OrderLine orderLine, int saleId) throws DatabaseAccessException;
}
