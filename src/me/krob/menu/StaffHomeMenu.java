package me.krob.menu;

import me.krob.Main;
import me.krob.session.UserSession;

import javax.swing.*;

public class StaffHomeMenu extends HomeMenu {
    private JPanel mainPanel;
    private JButton modifyProductsButton;
    private JButton viewOrdersButton;
    private JButton logoutButton;
    private JLabel greetingLabel;
    private JLabel displayLabel;

    public StaffHomeMenu(Main main) {
        super("Staff Home", main);
        setContentPane(mainPanel);

        logoutButton.addActionListener(e -> logout());

        modifyProductsButton.addActionListener(e -> {
            // Hiding menu
            dispose();

            // Showing modify products menu
            main.getModifyProductsMenu().setVisible(true);
        });

        viewOrdersButton.addActionListener(e -> {
            UserSession session = main.getUserSession();

            if (session.getOrders().isEmpty()) {
                updateDisplay("There are no viewable orders.");
                return;
            }

            dispose();
            main.getViewOrdersMenu().view();
        });
    }

    public void setGreetingText(String text) {
        greetingLabel.setText(text);
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }
}
