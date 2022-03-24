package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
	private int id;
	private LocalDateTime date;
	private LocalDateTime deliveryDate;
	private LocalDateTime paymentDate;	
	private Person customer;
	private String deliveryStatus;
	private List<OrderLine> orderLines;
	
	/**
	 * Constructor for the SaleOrder class
	 * @param customer the customer who is associated with the order
	 */
	public SaleOrder(Person customer) {
		orderLines = new ArrayList<>();
		this.customer = customer;
	}
	
	/**
	 * Add order line to the order
	 * @param orderLine The order line to be added
	 * @return true if the order line was successfully added
	 */
	public boolean addOrderLine(OrderLine orderLine) {
		return orderLines.add(orderLine);
	}
	
	/**
	 * Get sum of all order lines
	 * @return Get amount to pay from all order lines
	 */
	public BigDecimal getPrice() {
		double retVal = 0;
		
		for (OrderLine element : orderLines) {
			retVal += element.getAmount().doubleValue();
		}
		if(retVal >= 1500 && retVal <= 2500) {
			//pays for delivery but has discount
			retVal = retVal - 100 + 45;
		}
		else if(retVal >= 2500){
			// no delivery payment and has a discount
			retVal = retVal - 100;
		}
		else{
			//doesn't have a discount and pays for delivery
			retVal = retVal + 45;
		}
		
		return new BigDecimal(retVal).setScale(4, RoundingMode.CEILING);
	}
	
	/**
	 * Checks if a product is already present in the order
	 * @param product the product to search for
	 * @return true if the product is already present in the order
	 */
	public boolean isProductPresent(Product product) {
		boolean found = false;
		int index = 0;
		
		while (index < orderLines.size() && !found) {
			if (((SaleOrderLine)orderLines.get(index)).getProduct().getId() == product.getId()) {
				found = true;
			}
			else {
				index++;
			}
		}
		
		return found;
	}
	
	/**
	 * Finds an order line by the product in the order
	 * @param product the product to search for in the order
	 * @return the order line which contains the product
	 */
	public OrderLine getOrderLineByProduct(Product product) {
		boolean found = false;
		int index = 0;
		OrderLine retVal = null;
		
		while (index < orderLines.size() && !found) {
			if (((SaleOrderLine)orderLines.get(index)).getProduct().getId() == product.getId()) {
				retVal = orderLines.get(index);
				found = true;
			}
			else {
				index++;
			}
		}
		
		return retVal;
	}
	
	/**
	 * Removes an order line from the order which contains the product
	 * @param product the product to search for to remove the order line
	 * @return the quantity of product which was removed
	 */
	public int removeOrderLineByProduct(Product product) {
		boolean found = false;
		int index = 0;
		int retVal = 0;
		
		while (index < orderLines.size() && !found) {
			if (((SaleOrderLine)orderLines.get(index)).getProduct().getId() == product.getId()) {
				retVal = orderLines.get(index).getQuantity();
				orderLines.remove(index);
				found = true;
			}
			else {
				index++;
			}
		}
		
		return retVal;
	}

	/**
	 * @return boolean if sale order is empty
	 */
	public boolean isEmpty() {
		return orderLines.isEmpty();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * @return the deliveryDate
	 */
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the paymentDate
	 */
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the customer
	 */
	public Person getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	/**
	 * @return the deliveryStatus
	 */
	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * @param deliveryStatus the deliveryStatus to set
	 */
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	/**
	 * @return the orderLines
	 */
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	/**
	 * @param orderLines the orderLines to set
	 */
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
}
