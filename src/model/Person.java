package model;

public class Person {
	private String name;
	private String address;
	private String city;
	private String phoneno;
	private String zipcode;
	private String country;
	private String email;
	
	
	public Person(String name, String address, String city, String phone, String zipcode, String country, String email) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.phoneno = phone;
		this.zipcode = zipcode;
		this.country = country;
		this.email = email;
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


	public String getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(String phone) {
		this.phoneno = phone;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
