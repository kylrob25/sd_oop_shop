package me.krob.menu;

import me.krob.Main;
import me.krob.model.order.Order;
import me.krob.model.order.OrderLine;
import me.krob.util.TableUtil;
import me.krob.util.model.BasketTableModel;

import javax.swing.*;

public class ViewOrderMenu extends Menu {

    private JPanel mainPanel;
    private JTable lineTable;
    private JButton closeButton;
    private JLabel totalLabel;

    public ViewOrderMenu(Main main, Order order) {
        super("View Order", main);
        setContentPane(mainPanel);

        lineTable.setModel(new BasketTableModel(order));
        TableUtil.setCentreRenderer(lineTable);

        totalLabel.setText(String.format("Total: £%.2f",
                order.getLines().stream().mapToDouble(OrderLine::getTotal).sum()));

        closeButton.addActionListener(e -> {
            dispose(); // Hide menu
        });
    }

    public JLabel getDisplayLabel() {
        return null;
    }
}
