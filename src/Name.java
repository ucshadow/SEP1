import java.io.Serializable;

/**
 * A class containing names which later on will be used to create a reservation.
 *
 * @author Nikolay D Nikolov
 * @version 1.0
 */

public class Name implements Serializable {
    private String firstName;
    private String middleName;
    private String lastName;

    /**
     * Constructor for initializing Name.
     *
     * @param firstName  Constructor takes first name to initialize constructor
     * @param middleName Constructor takes middle name to initialize constructor
     * @param lastName   Constructor takes last name to initialize constructor
     */
    public Name(String firstName, String middleName, String lastName) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    /**
     * Gets the first name of a person.
     *
     * @return firstName The first name of a person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name of a person.
     * @param firstName takes the first name of a person.
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle name of a person.
     *
     * @return middleName The middle name of a person.
     */

    public String getMiddleName() {
        return middleName;
    }

    /**
     * Set middle name of a person.
     * @param middleName takes middle name of a person.
     */

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the last name of a person.
     *
     * @return lastName The last name of a person
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name of a person.
     * @param lastName takes last name of a person.
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * check if a name is equal to another name.
     * @param obj Object for comparing
     * @return true or false. if all names are equal after comparing with obj will return true, else will return false.
     */

    public boolean equals(Object obj) {
        if (!(obj instanceof Name)) {
            return false;
        }
        Name other = (Name) obj;
        return firstName.equals(other.firstName)
                && middleName.equals(other.middleName)
                && lastName.equals(other.lastName);
    }

    /**
     *  return a String
     * @return String containing first name, middle name, last name.
     */
    public String toString() {
        return firstName + middleName
                + lastName;
    }

}
