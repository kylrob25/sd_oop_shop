package classes;

public class User {
    private String username,
            password, firstName, lastName;

    /**
     * Empty constructor
     */
    public User() {
        this.username = password = firstName = lastName = "";
    }

    /**
     * Parameter Constructor
     * @param username - User Username
     * @param password - User Password
     * @param firstName - User First Name
     * @param lastName - User Last Name
     */
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
