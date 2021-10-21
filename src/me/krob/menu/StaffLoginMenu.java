package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.users.Staff;

import javax.swing.*;
import java.awt.*;

public class StaffLoginMenu extends JFrame{
    private final Main main;

    private JPanel mainPanel;
    private JButton mainMenuButton;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public StaffLoginMenu(Main main) {
        super("Staff Login");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

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

            Staff staff = main.getDbManager().getStaffDAO().get(username);

            // Couldn't find a staff member with the provided username
            if (staff == null) {
                JOptionPane.showMessageDialog(null, "Invalid username...");
                usernameField.setText(null);
                passwordField.setText(null);
                return;
            }

            // Passwords do not match
            if (!password.equals(staff.getPassword())) {
                JOptionPane.showMessageDialog(null, "Incorrect password...");
                passwordField.setText(null);
                return;
            }

            // Cleaning up this menu
            passwordField.setText(null);
            usernameField.setText(null);
            dispose();

            // Showing staff home menu
            // Showing customer home menu
            main.getStaffHomeMenu().login(staff);
        });
    }
}
