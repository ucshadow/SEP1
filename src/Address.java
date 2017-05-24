import java.io.Serializable;

/**
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
     * @param country  for initializing the constructor.
     * @param city     for initializing the constructor.
     * @param postCode for initializing the constructor.
     * @param street   for initializing the constructor.
     */

    public Address(String country, String city, String postCode, String street) {

        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
    }

    /**
     * Get a country of a person.
     *
     * @return Coutry Country of a person.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set a country for a person.
     *
     * @param country takes country for a person.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get a city of a person
     *
     * @return City City of a person.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set a city of a person.
     *
     * @param city takes city for a person
     */

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get a post code of a person
     *
     * @return Post code Post code of a person.
     */

    public String getPostCode() {
        return postCode;
    }

    /**
     * Set post code for a person.
     *
     * @param postCode takes post code for a person.
     */

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Get a street of a person.
     *
     * @return Street Street of a person.
     */

    public String getStreet() {
        return street;
    }

    /**
     * Set street for a person
     *
     * @param street takes a street for a person.
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
