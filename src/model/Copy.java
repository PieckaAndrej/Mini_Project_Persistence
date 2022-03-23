package model;
import java.time.LocalDate;

public class Copy {
	
	private int copyID;
	private boolean inRentable;
	private LocalDate rentDate;
	private Product product;
	
	//Constructor for Copy
	public Copy(int copyID, boolean inRentable, LocalDate rentDate) {
		this.copyID = copyID;
		inRentable = true;
		this.rentDate = rentDate;
	}
	//Returns the price of the copy
	public double getRentPrice() {
	return rentPrice();
	}
	//Sets the price of the copy
	public void setRentPrice(double rentPrice) {
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
