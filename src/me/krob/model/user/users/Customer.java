package me.krob.model.user.users;

import me.krob.model.order.Order;
import me.krob.model.user.User;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {
    private String addressLine1, addressLine2, town, postcode;
    private boolean registered;
    private Map<Integer, Order> orders;

    /**
     * Empty constructor
     */
    public Customer() {
        super();
        addressLine1 = addressLine2 = town = postcode = "";
        orders = new HashMap<>();
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
     * @param registered Is the customer registered
     */
    public Customer(String username, String password, String firstName, String lastName,
                    String addressLine1, String addressLine2, String town, String postcode, boolean registered) {
        super(username, password, firstName, lastName);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
        this.registered = registered;
        this.orders = new HashMap<>();
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

    public void setOrders(Map<Integer, Order> orders) {
        this.orders = orders;
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

    public Map<Integer, Order> getOrders() {
        return orders;
    }
}
