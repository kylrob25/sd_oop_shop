package me.krob.model.order;

import java.util.*;

public class Order {
    private int id;
    private Date date;
    private double total;
    private String status;
    private Map<Integer, OrderLine> lines;
    private List<OrderLine> lineIndex;

    /**
     * Top Constructor
     * @param date
     */
    Order(Date date) {
        this.date = date;
    }

    /**
     * Empty Constructor
     */
    public Order() {
        this(new Date());
        lines = new HashMap<>();
        lineIndex = new ArrayList<>();
        status = "In Progress";
    }

    /**
     * Parameter Constructor
     * @param id - Order ID
     * @param date - Order Date
     * @param total - Order Total
     * @param status - Order Status
     */
    public Order(int id, Date date, double total, String status) {
        this(date);

        this.id = id;
        this.total = total;
        this.status = status;
    }

    public double calculateTotal() {
        return total = lineIndex.stream().mapToDouble(OrderLine::getTotal).sum();
    }

    /**
     * Finding an existing line with the same product id
     * @param line - the new line
     * @return - the found line
     */
    public OrderLine hasProduct(OrderLine line) {
        int productId = line.getProduct().getId();

        for (OrderLine orderLine: lineIndex) {
            if (orderLine.getProduct().getId() == productId) {
                return orderLine;
            }
        }
        return null;
    }

    /**
     * Try modifying an existing line before adding a new one
     * @param line - the new line
     */
    public void tryOrderLine(OrderLine line) {
        OrderLine found = hasProduct(line);

        // There isn't a line with the same ID so we just add a new one
        if (found == null) {
            addOrderLine(line);
            return;
        }

        // Updating the existing lines quantity
        int quantity = line.getQuantity();
        found.addQuantity(quantity);
    }

    /**
     * Add a line to the order
     * @param line - the line to be added
     */
    public void addOrderLine(OrderLine line) {
        int id = 0;

        // If the id exists we increment the id to ensure it is unique
        while (lines.containsKey(id)) {
            id++;
        }

        // Setting the new id
        line.setId(id);

        // Storing the line
        lines.put(id, line);
        lineIndex.add(line);
    }

    public void removeOrderLine(OrderLine line) {
        lines.remove(line.getId());
        lineIndex.remove(line);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLines(Map<Integer, OrderLine> lines) {
        this.lines = lines;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public Map<Integer, OrderLine> getLines() {
        return lines;
    }

    public OrderLine getLineByIndex(int index) {
        return lineIndex.get(index);
    }

    public OrderLine getLine(int id) {
        return lines.get(id);
    }
}
