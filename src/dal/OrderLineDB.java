package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.DatabaseAccessException;
import model.OrderLine;
import model.SaleOrderLine;

public class OrderLineDB implements OrderLineDBIF {
	
	private static final String CREATE_SALE_ORDER_LINE_STATEMENT = "INSERT INTO SaleOrderLine(quantity, productId,"
			+ " saleId) VALUES(?, ?, ?)";
	private PreparedStatement createSaleOrderLineStatement;

	public OrderLineDB() throws DatabaseAccessException {
		try {
			createSaleOrderLineStatement = DbConnection.getInstance().getConnection().
					prepareStatement(CREATE_SALE_ORDER_LINE_STATEMENT);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DatabaseAccessException(DatabaseAccessException.CONNECTION_MESSAGE);
		}
	}
	
	/**
	 * Inserts the sale order line into the sale order line table or the rent order line table 
	 * into the rent order line table
	 * @param orderLine the orderLine to add to the table
	 * @param saleId the id of the sale which contains the order line
	 * @throws DatabaseAccessException
	 */
	@Override
	public void insertOrderLine(OrderLine orderLine, int saleId) throws DatabaseAccessException {
		if(orderLine instanceof SaleOrderLine) {
			SaleOrderLine saleOrderLine = (SaleOrderLine) orderLine;
			insertSaleOrderLine(saleOrderLine, saleId);
		}
	}
	
	/**
	 * Inserts the sale order line into the sale order line table
	 * @param orderLine the orderLine to add to the table
	 * @param saleId the id of the sale which contains the order line
	 * @throws DatabaseAccessException
	 */
	private void insertSaleOrderLine(SaleOrderLine orderLine, int saleId) throws DatabaseAccessException {
		try {
			createSaleOrderLineStatement.setInt(1, orderLine.getQuantity());
			createSaleOrderLineStatement.setInt(2, orderLine.getProduct().getId());
			createSaleOrderLineStatement.setInt(3, saleId);
			
			createSaleOrderLineStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DatabaseAccessException(e.getMessage());
		}
	}

}
