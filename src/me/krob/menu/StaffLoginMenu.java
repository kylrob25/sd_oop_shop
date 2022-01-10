package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.users.Staff;

import javax.swing.*;
import java.awt.*;

public class StaffLoginMenu extends Menu {
    private JPanel mainPanel;
    private JButton mainMenuButton;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel displayLabel;

    public StaffLoginMenu(Main main) {
        super("Staff Login", main);
        setContentPane(mainPanel);

        mainMenuButton.addActionListener(e -> {
            // Cleaning up this menu
            passwordField.setText(null);
            usernameField.setText(null);
            dispose();

            // Showing main menu
            main.getMainMenu().setVisible(true);
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            Staff staff = main.getDatabaseManager().getStaffDAO().get(username);

            // Couldn't find a staff member with the provided username
            if (staff == null) {
                updateDisplay("Invalid username...");
                usernameField.setText(null);
                passwordField.setText(null);
                return;
            }

            // Passwords do not match
            if (!password.equals(staff.getPassword())) {
                updateDisplay("Invalid username...");
                passwordField.setText(null);
                return;
            }

            // Cleaning up this menu
            passwordField.setText(null);
            usernameField.setText(null);
            dispose();

            // Showing staff home menu
            main.getStaffHomeMenu().staffLogin(staff);
        });
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }
}
