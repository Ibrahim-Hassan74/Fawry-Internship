package service;

import entities.Cart;
import entities.CartItem;
import entities.Customer;
import serviceContracts.ICheckoutService;
import serviceContracts.IShippableItem;
import serviceContracts.IShippingService;

import java.util.List;

public class CheckoutService implements ICheckoutService {

    private static final double SHIPPING_COST = 15.0;

    private final IShippingService shippingService = new ShippingService();

    @Override
    public void checkout(Customer customer, Cart cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        if (cart.hasExpiredProduct()) {
            throw new IllegalStateException("Cart contains expired product.");
        }

        double subtotal = cart.getSubtotal();
        List<CartItem> shippableItems = cart.getShippableItems();

        double shippingFees = shippableItems.isEmpty() ? 0 : SHIPPING_COST;
        double total = subtotal + shippingFees;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient balance.");
        }

        customer.pay(total);

        if (!shippableItems.isEmpty()) {
            shippingService.shipItems(shippableItems.stream()
                    .map(item -> (IShippableItem) item)
                    .toList());
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.2f\n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("Shipping: %.2f\n", shippingFees);
        System.out.printf("Amount paid: %.2f\n", total);
        System.out.printf("Customer new balance: %.2f\n", customer.getBalance());

        cart.clear();
    }
}
