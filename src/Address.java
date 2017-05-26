import java.io.Serializable;

/**
 * A class containing attributes that form an address which later on will be used to create a reservation.
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
     * Get a country of a guest.
     *
     * @return String, Country of a guest.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set a country of a guest.
     *
     * @param country a String representing a new Country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get a city of a guest
     *
     * @return String, City of a guest.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set a city of a guest.
     *
     * @param city a String representing a new city
     */

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get a post code of a guest
     *
     * @return String, post code of a guest.
     */

    public String getPostCode() {
        return postCode;
    }

    /**
     * Set post code of a guest.
     *
     * @param postCode a String representing a new post code
     */

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Get a street of a guest.
     *
     * @return String Street of a guest.
     */

    public String getStreet() {
        return street;
    }

    /**
     * Set street for a guest
     *
     * @param street a String representing a new street
     */

    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * check if a address is equal to another address.
     *
     * @param obj Object for comparing
     * @return true or false. if the whole address is equal after comparing with obj will return true, else will return false.
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
     * @return String containing country, city,post code, street.
     */
    public String toString() {
        return ", Country :" + country + ", city " + city + " ,postcode "
                + postCode + " ,street " + street;
    }
}
