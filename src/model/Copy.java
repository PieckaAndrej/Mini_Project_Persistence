package model;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Copy {
	
	private int copyID;
	private boolean inRentable;
	private LocalDate rentDate;
	private BigDecimal rentPrice;
	private Product product;
	
	//Constructor for Copy
	public Copy(int copyID, boolean inRentable, LocalDate rentDate) {
		this.copyID = copyID;
		inRentable = true;
		this.rentDate = rentDate;
	}
	//Returns the price of the copy
	public BigDecimal getRentPrice() {
		return rentPrice;
	}
	//Sets the price of the copy
	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}
	//Checks if the copy is rentable
	public boolean inRentable() {
		return inRentable;
	}
	//Sets the copy to rentable or not
	public void setInRentable(boolean inRentable) {
		this.inRentable = inRentable;
	}
}
