package dal;

import model.SaleOrder;

public interface OrderDBIF {
	public boolean createOrder(SaleOrder order);
}
