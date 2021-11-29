package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.users.Customer;
import me.krob.storage.dao.CustomerDAO;

import javax.swing.*;
import java.util.Arrays;

import static me.krob.util.StringUtil.*;

public class RegistrationMenu extends Menu {
    private static final String PASSWORD_VALIDATION_HTML = "<html>" +
            " Passwords must be:" +
            " <ul>" +
            "<li>At least 8 characters</li>" +
            " <li> Contain at least one digit</li>" +
            " <li>Contain at least one uppercase letter</li>" +
            "</ul>" +
            " </html>";

    private JPanel mainPanel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JButton clearButton;
    private JButton backButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPanel addressPanel;
    private JTextField addressLine1Field;
    private JTextField addressLine2Field;
    private JTextField townField;
    private JTextField postCodeField;
    private JLabel displayLabel;
    private JTextField phoneField;

    public RegistrationMenu(Main main) {
        super("Registration", main);
        setContentPane(mainPanel);
        setSize(500, 450);

        backButton.addActionListener(e -> {
            // Cleaning and hiding this menu
            clearFields();
            dispose();

            // Showing the customer login menu
            main.getCustomerLoginMenu().setVisible(true);
        });

        // Clearing all the fields
        clearButton.addActionListener(e -> clearFields());

        // Try and register an account
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();

            String addressLine1 = addressLine1Field.getText();
            String addressLine2 = addressLine2Field.getText();
            String town = townField.getText();
            String postcode = postCodeField.getText();

            // Ensuring none of the fields are null
            if (isEmpty(username) || isEmpty(password) || isEmpty(firstName) || isEmpty(lastName) ||
                    isEmpty(addressLine1) || isEmpty(addressLine2) || isEmpty(town) || isEmpty(postcode)) {
                JOptionPane.showMessageDialog(null, "Please ensure all fields are not empty!");
                return;
            }

            CustomerDAO customerDAO = main.getDatabaseManager().getCustomerDAO();

            // Garbage collection will get this
            Customer customer = customerDAO.get(username);

            if (customer != null) {
                // Clearing username field
                usernameField.setText(null);

                // Displaying error
                JOptionPane.showMessageDialog(null, "This username is already in use!");
                return;
            }

            // Password validation
            if (password.length() <= 8 || !hasDigit(password) || !hasUpperCase(password)) {
                passwordField.setText(null);
                JOptionPane.showMessageDialog(null, PASSWORD_VALIDATION_HTML);
                return;
            }

            if (!isValidPostCode(postcode)) {
                postCodeField.setText(null);
                JOptionPane.showMessageDialog(null, "Please enter a valid UK postcode!");
                return;
            }

            customer = new Customer(username, password, firstName,
                    lastName, addressLine1, addressLine2, town, postcode, true);

            boolean registered = customerDAO.insert(customer);
            if (registered) {
                // Displaying success message
                JOptionPane.showMessageDialog(null, "You have successfully registered an account!");

                // Cleaning up this menu
                clearFields();
                dispose();

                // Showing customer login menu
                main.getCustomerLoginMenu().setVisible(true);
                return;
            }

            // Displaying error message
            JOptionPane.showMessageDialog(null, "There was an issue whilst registering your account.");

            // Clearing fields
            clearFields();
        });
    }

    /**
     * Clearing all the text fields
     */
    private void clearFields() {
        Arrays.asList(
                usernameField,
                passwordField,
                firstNameField,
                lastNameField, addressLine1Field,
                addressLine2Field, townField,
                postCodeField
        ).forEach(field -> field.setText(null));
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }
}
