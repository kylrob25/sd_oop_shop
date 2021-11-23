package me.krob.menu;

import me.krob.Main;
import me.krob.model.order.Order;
import me.krob.session.UserSession;
import me.krob.util.TableUtil;
import me.krob.util.model.BasketTableModel;

import javax.swing.*;

public class ViewBasketMenu extends Menu {
    private JPanel mainPanel;
    private JTable basketTable;
    private JButton backButton;

    public ViewBasketMenu(Main main, Order order) {
        super("View Basket", main);
        setContentPane(mainPanel);

        basketTable.setModel(new BasketTableModel(order));
        TableUtil.setCentreRenderer(basketTable);

        backButton.addActionListener(e -> {
            dispose();

            main.getBrowseProductsMenu().view();
        });
    }
}
