package me.krob.model.order;

import me.krob.model.product.Product;

public class OrderLine {
    private int id;
    private Product product;
    private int quantity;
    private double total;

    /**
     * Parameter Constructor
     * @param  - OrderLine ID
     * @param product - OrderLine Product
     * @param quantity - OrderLine Quantity
     * @param total - OrderLine Total
     */
    public OrderLine(int id, Product product, int quantity, double total) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }

    /**
     * Parameter Constructor (without total)
     * @param id - OrderLine ID
     * @param product - OrderLine Product
     * @param quantity - OrderLine Quantity
     */
    public OrderLine(int id, Product product, int quantity) {
        this(id, product, quantity, product.getPrice() * quantity);
    }

    /**
     * Constructor without ID nor total
     * @param product - OrderLine Product
     * @param quantity - OrderLine Quantity
     */
    public OrderLine(Product product, int quantity) {
        this(0, product, quantity, product.getPrice() * quantity);
    }

    /**
     * Removing quantity and updating total
     * @param quantity - amount to remove
     */
    public void removeQuantity(int quantity) {
        total = product.getPrice() * (this.quantity -= quantity);
    }

    /**
     * Adding quantity and updating total
     * @param quantity - amount to add
     */
    public void addQuantity(int quantity) {;
        total = product.getPrice() * (this.quantity += quantity);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }
}
