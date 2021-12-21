package me.krob.menu;

import me.krob.Main;
import me.krob.session.UserSession;
import me.krob.util.TableUtil;
import me.krob.util.model.OrderTableModel;

import javax.swing.*;

public class ViewOrdersMenu extends Menu {
    private static final OrderTableModel MODEL = new OrderTableModel();

    private JPanel mainPanel;
    private JTable orderTable;
    private JButton backButton;

    public ViewOrdersMenu(Main main) {
        super("View Orders", main);
        setContentPane(mainPanel);

        orderTable.setModel(MODEL);
        TableUtil.setCentreRenderer(orderTable);

        backButton.addActionListener(e -> {
            dispose();

            main.getCustomerHomeMenu().setVisible(true);
        });
    }

    public void view() {
        UserSession session = main.getUserSession();
        if (session.isActive()) {
            MODEL.loadOrders(session); // Loading orders
        }

        setVisible(true);
    }

    public JLabel getDisplayLabel() {
        return null;
    }
}
