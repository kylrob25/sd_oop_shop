package me.krob.model.product.products;

import me.krob.model.product.Product;

public class Clothing extends Product {
    private String measurement;

    /**
     * Empty Constructor
     */
    public Clothing() {
        super();
        measurement = "";
    }

    /**
     * Parameter Constructor
     * @param name - Clothing Name
     * @param price - Clothing Price
     * @param stockLevel - Clothing Stock Level
     * @param measurement - Clothing Measurement
     */
    public Clothing(String name, double price, int stockLevel, String measurement) {
        super(name, price, stockLevel);
        this.measurement = measurement;
    }

    /**
     * Parameter Constructor
     * @param id - Clothing ID
     * @param name - Clothing Name
     * @param price - Clothing Price
     * @param stockLevel - Clothing Stock Level
     * @param measurement - Clothing Measurement
     */
    public Clothing(int id, String name, double price, int stockLevel, String measurement) {
        super(id, name, price, stockLevel);
        this.measurement = measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getMeasurement() {
        return measurement;
    }
}
