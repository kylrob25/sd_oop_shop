package me.krob.model.order;

import java.util.*;

public class Order {
    private int id;
    private Date date;
    private double total;
    private String status;
    private Map<Integer, OrderLine> lines;

    private List<OrderLine> lineIndex = new ArrayList<>();

    /**
     * Empty Constructor
     */
    public Order() {
        date = new Date();
        lines = new HashMap<>();
    }


    /**
     * Parameter Constructor
     * @param id - Order ID
     * @param date - Order Date
     * @param total - Order Total
     * @param status - Order Status
     */
    public Order(int id, Date date, double total, String status) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.status = status;

        lines = new HashMap<>();
    }

    public void addOrderLine(OrderLine line) {
        int id = 0;

        while (lines.containsKey(id)) {
            id++;
        }

        line.setId(id);
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
