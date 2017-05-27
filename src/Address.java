import java.io.Serializable;

/**
 * A class containing attributes that create an address which later on will be used to create a reservation.
 * @author Nikolay D Nikolov
 * @version 1.0
 */

public class Address implements Serializable {
    private String country;
    private String city;
    private String postCode;
    private String street;

    /**
     * Constructor initializing Address
     *
     * @param country  for initializing inside the constructor.
     * @param city     for initializing inside the constructor.
     * @param postCode for initializing inside the constructor.
     * @param street   for initializing inside the constructor.
     */

    public Address(String country, String city, String postCode, String street) {

        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
    }

    /**
     * Gets the country of a guest.
     *
     * @return String, representing the country of a guest.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Replaces the country of a guest.
     *
     * @param country a String representing the country to replace with.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the city of a guest
     *
     * @return String, representing the city of a guest.
     */
    public String getCity() {
        return city;
    }

    /**
     * Replaces the city of a guest.
     *
     * @param city a String representing the city to replace with
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the post code of a guest
     *
     * @return String, representing post code of a guest.
     */

    public String getPostCode() {
        return postCode;
    }

    /**
     * Replaces the post code of a guest.
     *
     * @param postCode a String representing the post code to replace with
     */

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Gets the street of a guest.
     *
     * @return String, representing the street of a guest.
     */

    public String getStreet() {
        return street;
    }

    /**
     * Replaces the street of a guest
     *
     * @param street a String representing the street to replace with
     */

    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Checks if the address is equal to another address.
     *
     * @param obj Object for comparing
     * @return true or false. If the entire address is equal to obj, the method will return true, else it returns false.
     */

    public boolean equals(Object obj) {
        if (!(obj instanceof Address)) {
            return false;
        }
        Address other = (Address) obj;

        return country.equals(other.country) && city.equals(other.city)
                && postCode.equals(other.postCode)
                && street.equals(other.street);
    }

    /**
     * return a String
     *
     * @return String, representing country, city, post code and street.
     */

    public String toString() {
        return ", Country :" + country + ", city " + city + " ,postcode "
                + postCode + " ,street " + street;
    }
}
