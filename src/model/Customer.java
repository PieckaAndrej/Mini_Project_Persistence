package model;

public class Customer {
	private String name;
	private String address;
	private String city;
	private String phone;
	private int zipcode;
	
	
	public Customer(String name, String address, String city, String phone, int zipcode) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.zipcode = zipcode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getZipcode() {
		return zipcode;
	}


	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
