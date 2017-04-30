public class Address {
	private String country;
	private String city;
	private String postCode;
	private String street;

	public Address(String country, String city, String postCode, String street) {

		this.country = country;
		this.city = city;
		this.postCode = postCode;
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Address)) {
			return false;
		}
		Address other = (Address) obj;

		return country.equals(other.country) && city.equals(other.city)
				&& postCode.equals(other.postCode)
				&& street.equals(other.street);
	}

	public String toString() {
		return ", Country :" + country + ", city " + city + " ,postcode "
				+ postCode + " ,street " + street;
	}
}
