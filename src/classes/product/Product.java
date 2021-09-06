package classes.product;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stockLevel;

    /**
     * Empty constructor
     */
    public Product() {
        // Primitive types have default values in Java
        this.name = "";
    }

    /**
     * Constructor with all parameters
     * @param id - Product ID
     * @param name - Product Name
     * @param price - Product Price
     * @param stockLevel - Product Stock Level
     */
    public Product(int id, String name, double price, int stockLevel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockLevel = stockLevel;
    }

    /**
     * Constructor with 3 parameters
     * @param name - Product Name
     * @param price - Product Price
     * @param stockLevel - Product Stock Level
     */
    public Product(String name, double price, int stockLevel) {
        this(0, name, price, stockLevel);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel =  stockLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockLevel() {
        return stockLevel;
    }
}
