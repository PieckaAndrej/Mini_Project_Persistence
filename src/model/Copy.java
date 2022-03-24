package model;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Copy {
	
	private int copyID;
	private LocalDate rentDate;
	private BigDecimal rentPrice;
	private Product product;
	
	
	/**
	 * Constructor for the Copy class
	 * @param copyID the id of the copy
	 * @param rentDate the end date of the rent
	 * @param rentPrice the price of the renting the copy
	 * @param product the product of which the copy is
	 */
	public Copy(int copyID, LocalDate rentDate, BigDecimal rentPrice, Product product) {
		super();
		this.copyID = copyID;
		this.rentDate = rentDate;
		this.rentPrice = rentPrice;
		this.product = product;
	}


	/**
	 * @return the copyID
	 */
	public int getCopyID() {
		return copyID;
	}


	/**
	 * @param copyID the copyID to set
	 */
	public void setCopyID(int copyID) {
		this.copyID = copyID;
	}


	/**
	 * @return the rentDate
	 */
	public LocalDate getRentDate() {
		return rentDate;
	}


	/**
	 * @param rentDate the rentDate to set
	 */
	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}


	/**
	 * @return the rentPrice
	 */
	public BigDecimal getRentPrice() {
		return rentPrice;
	}


	/**
	 * @param rentPrice the rentPrice to set
	 */
	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}


	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}


	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
