package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;

public class StaffLoginMenu extends JFrame{
    private final Main main;

    private JPanel mainPanel;
    private JButton mainMenuButton;
    private JButton loginButton;
    private JTextField usernameField;
    private JTextField textField2;
    private JLabel passwordField;

    public StaffLoginMenu(Main main) {
        super("Staff Login");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

        mainMenuButton.addActionListener(e -> {
            this.setVisible(false);
            main.getMainMenu().setVisible(true);
        });
    }
}
