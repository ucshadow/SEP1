import java.io.Serializable;

/**
 * A class containing guest which later on will be used to create a reservation.
 * @author Yusuf A Farah
 * @version 1.0
 */
public class Guest implements Serializable {

    private Name name;
    private long phoneNumber;
    private Address address;
    private String nationality;
    private String dateOfBirth;

    /**
     * Constructor initializing Guest.
     *
     * @param name        for initializing the constructor.
     * @param phoneNumber for initializing the constructor.
     * @param address     for initializing the constructor.
     * @param nationality for initializing the constructor.
     * @param dateOfBirth for initializing the constructor.
     */

    public Guest(Name name, long phoneNumber, Address address,
                 String nationality, String dateOfBirth) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get a names for a guest.
     *
     * @return name names for a guest.
     */
    public Name getName() {
        return name;
    }

    /**
     * Set names for a guest
     *
     * @param name takes name for a guest.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Get a phone number for a guest.
     *
     * @return phoneNumber  Phone number of a guest.
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set phone number for a guest.
     *
     * @param phoneNumber takes phone number for a guest.
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get a addres for a guest.
     *
     * @return address Address for a guest.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set address for a guest.
     *
     * @param address takes address for a guest
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get a nationality for a guest.
     *
     * @return nationality Nationality for a guest.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Set nationality for a guest.
     *
     * @param nationality takes Nationality for a guest.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Get Birthday for a guest.
     *
     * @return dateOfBirth Birthday for a guest.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set birthday for a guest.
     *
     * @param dateOfBirth takes Birthday for a guest.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get copy of a guest.
     *
     * @return Guest copy of a guest.
     */
    public Guest copy() {
        return new Guest(name, phoneNumber, address, nationality,
                dateOfBirth);
    }

    /**
     * check if a guest is equal to another guest.
     *
     * @param obj Object for comparing
     * @return true or false. if the guest is equal after comparing with obj will return true, else will return false.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Guest)) {
            return false;
        }
        Guest other = (Guest) obj;
        return name.equals(other.name) && phoneNumber == other.phoneNumber
                && address.equals(other.address)
                && nationality.equals(other.nationality)
                && dateOfBirth.equals(other.dateOfBirth);
    }

    /**
     * return a String
     *
     * @return String containing name, address, phone number, nationality, birthday.
     */
    public String toString() {
        return name.toString() + address.toString() + ", phone number "
                + phoneNumber + ", nationality " + nationality
                + ", date of birth" + dateOfBirth;
    }

}
