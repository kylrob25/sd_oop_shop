package menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private JButton customerLoginButton;
    private JButton staffLogin;
    private JButton viewProductsButton;
    private JPanel mainPanel;

    public MainMenu() {
        super("Main Menu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(new Dimension(300, 200));
        setResizable(false);
        pack();

        setVisible(true);
    }
}
