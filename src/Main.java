import classes.order.Order;
import classes.order.OrderLine;
import classes.product.Product;
import classes.user.User;
import classes.user.users.Customer;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("TESTING");

        Customer customer = new Customer();
        Product product = new Product(0, "Water Bottle",1.29, 500);
        Product product1 = new Product(1, "Red Bull",1.75, 500);

        Order order = new Order();
        customer.getOrders().put(order.getId(), order);

        OrderLine orderLine = new OrderLine(0, product, 20);
        OrderLine orderLine1 = new OrderLine(1, product1, 4);

        order.getLines().put(orderLine.getId(), orderLine);
        order.getLines().put(orderLine1.getId(), orderLine1);

        customer.getOrders().forEach((id, o) -> {
            order.getLines().forEach((i, line) -> {
                System.out.printf("ID: %s | Total: %s | Product: %s | Quantity: %s%n", i, line.getTotal(),
                        line.getProduct().getName(), line.getQuantity());
            });
        });
    }
}
