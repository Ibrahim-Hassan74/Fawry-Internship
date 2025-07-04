import entities.Cart;
import entities.Customer;
import entities.Product;
import service.AuthService;
import service.CheckoutService;
import service.ProductService;
import serviceContracts.IAuthService;
import serviceContracts.ICheckoutService;
import serviceContracts.IProductService;

import java.util.List;
import java.util.Scanner;

public class ShoppingApp {
    private final Scanner scanner = new Scanner(System.in);
    private final IAuthService authService = new AuthService();
    private final ProductService productService = new ProductService();
    private final ICheckoutService checkoutService = new CheckoutService(productService);

    public void run() {
        System.out.println("==== Welcome to the Shopping App ====");

        Customer currentCustomer = null;

        while (currentCustomer == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                currentCustomer = handleLogin();
            } else if (option.equals("2")) {
                handleRegister();
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        Cart cart = new Cart();
        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Buy products");
            System.out.println("2. Add funds");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleAddProductsToCart(cart);
                case "2" -> handleAddFunds(currentCustomer);
                case "3" -> {
                    try {
                        checkoutService.checkout(currentCustomer, cart);
                    } catch (Exception e) {
                        System.out.println("Checkout failed: " + e.getMessage());
                    }
                }
                case "4" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private Customer handleLogin() {
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            return authService.login(username, password);
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }

    private void handleRegister() {
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Confirm Password: ");
            String confirm = scanner.nextLine();
            System.out.print("Initial Balance: ");
            double balance = Double.parseDouble(scanner.nextLine());

            authService.register(username, password, confirm, balance);
            System.out.println("Registered successfully! Please log in.");
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void handleAddFunds(Customer customer) {
        try {
            System.out.print("Enter amount to add: ");
            double amount = Double.parseDouble(scanner.nextLine());
            customer.addFunds(amount);
            System.out.printf("Balance updated: %.2f\n", customer.getBalance());
        } catch (Exception e) {
            System.out.println("Failed to add funds: " + e.getMessage());
        }
    }

    private void handleAddProductsToCart(Cart cart) {
        List<Product> products = productService.loadProducts();
        System.out.println("=== Available Products ===");
        for (Product product : products) {
            System.out.printf("ID: %d - %s - Price: %.2f - In Stock: %d\n",
                    product.getId(), product.getName(), product.getPrice(), product.getQuantity());
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            System.out.print("Enter product ID to add: ");
            int id = Integer.parseInt(scanner.nextLine());
            Product selected = productService.findById(id);

            if (selected == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            cart.addProduct(selected, quantity);
            System.out.printf("%d x %s added to cart.\n", quantity, selected.getName());

        } catch (Exception e) {
            System.out.println("Failed to add product: " + e.getMessage());
        }
    }
}
