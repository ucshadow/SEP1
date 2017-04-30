public class GuestData {

	private Name name;
	private long phoneNumber;
	private Address address;
	private String nationality;
	private String dateOfBirth;

	public GuestData(Name name, long phoneNumber, Address address,
			String nationality, String dateOfBirth) {

		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		nationality = nationality;
		this.dateOfBirth = dateOfBirth;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public GuestData copy() {
		return new GuestData(name, phoneNumber, address, nationality,
				dateOfBirth);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof GuestData)) {
			return false;
		}
		GuestData other = (GuestData) obj;
		return name.equals(other.name) && phoneNumber == other.phoneNumber
				&& address.equals(other.address)
				&& nationality.equals(other.nationality)
				&& dateOfBirth.equals(other.dateOfBirth);
	}

	public String toString() {
		return name.toString() + address.toString() + ", phone number "
				+ phoneNumber + ", nationality " + nationality
				+ ", date of birth" + dateOfBirth;
	}

}
