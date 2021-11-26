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
}
