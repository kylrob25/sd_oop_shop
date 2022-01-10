package me.krob.menu;

import me.krob.Main;
import me.krob.model.order.Order;
import me.krob.session.UserSession;
import me.krob.storage.dao.OrderDAO;
import me.krob.storage.dao.OrderLineDAO;
import me.krob.util.TableUtil;
import me.krob.util.model.OrderTableModel;

import javax.swing.*;

public class ViewOrdersMenu extends Menu {
    private static final OrderTableModel MODEL = new OrderTableModel();

    private JPanel mainPanel;
    private JTable orderTable;
    private JButton backButton;
    private JLabel totalLabel;
    private JButton viewSelectedButton;

    public ViewOrdersMenu(Main main) {
        super("View Orders", main);
        setContentPane(mainPanel);

        orderTable.setModel(MODEL);
        TableUtil.setCentreRenderer(orderTable);

        backButton.addActionListener(e -> {
            dispose();

            if (main.getUserSession().isStaff()) {
                main.getStaffHomeMenu().setVisible(true);
                return;
            }

            main.getCustomerHomeMenu().setVisible(true);
        });

        viewSelectedButton.addActionListener(e -> {
            int row = orderTable.getSelectedRow();
            Order order = MODEL.getOrders().get(row);

            if (order != null) {
                Order cloned = order.clone(); // Temp clone

                OrderLineDAO orderLineDAO = main.getDatabaseManager().getOrderLineDAO();
                orderLineDAO.getValues().stream()
                        .filter(line -> line.getOrderId() == cloned.getId())
                        .forEach(line -> cloned.addOrderLine(line, false)); // Adding lines to the clone

                new ViewOrderMenu(main, cloned).setVisible(true); // Showing order
            }
        });
    }

    public void view() {
        setVisible(true);

        UserSession session = main.getUserSession();
        if (!session.isStaff()) {
            MODEL.loadOrders(session); // Loading orders
        } else {
            MODEL.loadAllOrders(main.getDatabaseManager().getOrderDAO());
        }

        totalLabel.setText(String.format("Total Orders: £%.2f",
                MODEL.getOrders().stream().mapToDouble(Order::getTotal).sum()));
    }

    public JLabel getDisplayLabel() {
        return null;
    }
}
