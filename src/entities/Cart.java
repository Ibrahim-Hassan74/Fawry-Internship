package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough quantity in stock.");
        }

        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProduct().getId() == product.getId())
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + quantity;

            if (newQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("Requested quantity exceeds stock.");
            }

            items.remove(item);
            items.add(new CartItem(product, newQuantity));
        } else {
            items.add(new CartItem(product, quantity));
        }
    }

    public List<CartItem> getItems() {
        return List.copyOf(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(item -> item.getTotalPrice()).sum();
    }

    public List<CartItem> getShippableItems() {
        return items.stream()
                .filter(item -> item.isShippable())
                .toList();
    }

    public boolean hasExpiredProduct() {
        return items.stream().anyMatch(item -> item.isExpired());
    }

    public void clear() {
        items.clear();
    }
}
