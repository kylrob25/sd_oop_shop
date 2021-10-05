package me.krob.model.user;

public abstract class User {
    private static final String GREETING = "<html> Hello %s<br>You are logged in as a %s</html>";

    private String username,
            password, firstName, lastName;

    /**
     * Empty constructor
     */
    public User() {
        username = password = firstName = lastName = "";
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

    public String getDisplayGreeting(String userType) {
        return String.format(GREETING, getFirstName(), userType);
    }

    public abstract String getDisplayGreeting();
}
