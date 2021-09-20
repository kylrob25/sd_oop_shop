package model.product.products;

import model.product.Product;

public class Footwear extends Product {
    private int size;

    /**
     * Empty Constructor
     */
    public Footwear() {
        super();
    }

    /**
     * Parameter Constructor
     * @param name - Footwear Name
     * @param price - Footwear Price
     * @param stockLevel - Footwear Stock Level
     * @param size - Footwear Size
     */
    public Footwear(String name, double price, int stockLevel, int size) {
        super(name, price, stockLevel);
        this.size = size;
    }

    /**
     * Parameter Constructor
     * @param id - Footwear ID
     * @param name - Footwear Name
     * @param price - Footwear Price
     * @param stockLevel - Footwear Stock Level
     * @param size - Footwear Size
     */
    public Footwear(int id, String name, double price, int stockLevel, int size) {
        super(id, name, price, stockLevel);
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
