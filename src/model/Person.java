package model;

public class Person {
	private String name;
	private String address;
	private String city;
	private String phoneno;
	private String zipcode;
	private String country;
	private String email;
	
	
	/**
	 * COnstructor for Person class
	 * @param name the name of the person
	 * @param address the address of the person
	 * @param city the city of the person
	 * @param phone the phone number of the person
	 * @param zipcode the zip code of the person
	 * @param country the country of the person
	 * @param email the email of the person
	 */
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


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the phoneno
	 */
	public String getPhoneno() {
		return phoneno;
	}


	/**
	 * @param phoneno the phoneno to set
	 */
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}


	/**
	 * @param zipcode the zipcode to set
	 */
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
