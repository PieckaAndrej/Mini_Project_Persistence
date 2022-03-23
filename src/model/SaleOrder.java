package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
	private LocalDateTime date;
	private LocalDateTime deliveryDate;
	private LocalDateTime paymentDate;
	
	private Person customer;
	private String deliveryStatus;
	private List<OrderLine> orderLines;
	
	public SaleOrder(Person customer) {
		orderLines = new ArrayList<>();
		this.customer = customer;
	}
	
	/**
	 * Add orderline to the order
	 * @param orderLine The orderline to be added
	 * @return true if the orderline was successfully added
	 */
	public boolean addOrderLine(OrderLine orderLine) {
		return orderLines.add(orderLine);
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public List<OrderLine> getOrderLines() {
		return new ArrayList<>(orderLines);
	}
	
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
 
	/**
	 * Get sum of all orderlines
	 * @return Get amount from all orderlines
	 */
	public double getAmount() {
		double retVal = 0;
		
		for (OrderLine element : orderLines) {
			retVal += element.getAmount();
		}
		
		return retVal;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}
	
}
