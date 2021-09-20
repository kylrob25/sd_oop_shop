package model.user.users;

import model.user.User;

public class Staff extends User {
    private String position;
    private double salary;

    /**
     * Empty Constructor
     */
    public Staff() {
        super();
        position = "";
    }

    /**
     * Parameter Constructor
     * @param username - Staff Username
     * @param password - Staff Password
     * @param firstName - Staff First Name
     * @param lastName - Staff Last Name
     * @param position - Staff Position
     * @param salary - Staff Salary
     */
    public Staff(String username, String password, String firstName, String lastName,
                    String position, double salary) {
        super(username, password, firstName, lastName);
        this.position = position;
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}
