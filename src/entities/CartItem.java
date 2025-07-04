package entities;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (quantity <= 0 || quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Invalid quantity.");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public boolean isExpired() {
        return product.isExpired();
    }

    public boolean isShippable() {
        return product.isShippable();
    }

    public double getTotalWeight() {
        return product.getWeightInGrams() * quantity;
    }
}
