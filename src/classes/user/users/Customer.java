package classes.user.users;

import classes.user.User;

public class Customer extends User {
    private String addressLine1, addressLine2, town, postcode;
    private boolean registered;

    /**
     * Empty constructor
     */
    public Customer() {
        super();
        addressLine1 = addressLine2 = town = postcode = "";
    }

    /**
     * Parameter Constructor
     * @param username = Customer Username
     * @param password = Customer Password
     * @param firstName - Customer First Name
     * @param lastName - Customer Last Name
     * @param addressLine1 - Customer Address Line 1
     * @param addressLine2 - Customer Adresss Line 2
     * @param town Customer Town
     * @param postcode Customer Postcode
     */
    public Customer(String username, String password, String firstName, String lastName,
                    String addressLine1, String addressLine2, String town, String postcode) {
        super(username, password, firstName, lastName);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getTown() {
        return town;
    }

    public String getPostcode() {
        return postcode;
    }

    public boolean isRegistered() {
        return registered;
    }
}
