package model;

import java.math.BigDecimal;
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
	public BigDecimal getAmount() {
		double retVal = 0;
		
		for (OrderLine element : orderLines) {
			retVal += element.getAmount().doubleValue();
		}
		
		if(retVal >= 2500)
		{
			retVal = retVal - 100;
		}
		else
		{
			retVal = retVal + 45;
		}
		
		return new BigDecimal(retVal);
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isEmpty() {
		return orderLines.isEmpty();
	}
	
}
