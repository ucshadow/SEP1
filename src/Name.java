
public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public Name(String firstName, String middleName, String lastName) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Name)) {
			return false;
		}
		Name other = (Name) obj;
		return firstName.equals(other.firstName)
				&& middleName.equals(other.middleName)
				&& lastName.equals(other.lastName);
	}

	public String toString() {
		return "First name :" + firstName + ", middle name : " + middleName
				+ ", last name :" + lastName;
	}

}
