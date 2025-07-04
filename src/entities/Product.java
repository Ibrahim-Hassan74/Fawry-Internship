package entities;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    private boolean isExpirable;
    private LocalDate expiryDate;

    private boolean isShippable;
    private double weightInGrams;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity, boolean isExpirable,
                   LocalDate expiryDate, boolean isShippable, double weightInGrams) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExpirable = isExpirable;
        this.expiryDate = expiryDate;
        this.isShippable = isShippable;
        this.weightInGrams = weightInGrams;
    }

    public boolean isExpired() {
        return isExpirable && expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }

    public void reduceQuantity(int amount) {
        if (amount > quantity)
            throw new IllegalArgumentException("Insufficient quantity");
        quantity -= amount;
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

    public int getQuantity() {
        return quantity;
    }

    public boolean isShippable() {
        return isShippable;
    }

    public double getWeightInGrams() {
        return weightInGrams;
    }
}
