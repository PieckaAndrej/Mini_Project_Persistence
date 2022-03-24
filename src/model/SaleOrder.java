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
	
	/**
	 * @return date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	/**
	 * @return delivery date
	 */
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 */
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return delivery status
	 */
	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * @param deliveryStatus
	 */
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	/**
	 * @return array list of order lines
	 */
	public List<OrderLine> getOrderLines() {
		return new ArrayList<>(orderLines);
	}
	
	/**
	 * @return paymentDate
	 */
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 */
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
 
	/**
	 * Get sum of all orderlines
	 * @return Get amount from all orderlines
	 */
	/**
	 * @return amount to pay
	 */
	public BigDecimal getAmount() {
		double retVal = 0;
		
		for (OrderLine element : orderLines) {
			retVal += element.getAmount().doubleValue();
		}
		if(retVal >= 1500) {
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
	
	public BigDecimal getPrice()
	{
		double retVal = 0;
		
		for (OrderLine element : orderLines) {
			retVal += element.getAmount().doubleValue();
		}
		
		return new BigDecimal(retVal).setScale(4, RoundingMode.CEILING);
	}

	/**
	 * @return customer
	 */
	public Person getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 */
	public void setCustomer(Person customer) {
		this.customer = customer;
	}
	
	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return boolean if saleorder is empty
	 */
	public boolean isEmpty() {
		return orderLines.isEmpty();
	}
	
}
