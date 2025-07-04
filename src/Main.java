import entities.Product;
import service.ProductService;

public class Main {
    public static void main(String[] args) {
        var product = new ProductService();
//        String path = ClassLoader.getSystemResource().getPath();
        var products = product.loadProducts();

        System.out.println("Available Products:");
        for (Product p : products) {
            System.out.printf("[%d] %s - $%.2f - Quantity: %d\n",
                    p.getId(), p.getName(), p.getPrice(), p.getQuantity());
        }

    }
}


/*
*
public class Main {
    public static void main(String[] args) {
        // Step 1: Load services
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        AuthService authService = new AuthService(customerService);
        CartService cartService = new CartService();
        CheckoutService checkoutService = new CheckoutService();
        ShippingService shippingService = new ShippingService();

        // Step 2: Console logic
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Fawry eCommerce System");

        Customer currentUser = null;

        while (currentUser == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Username: ");
                String username = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();

                currentUser = authService.login(username, password);
                if (currentUser == null) {
                    System.out.println("Invalid credentials. Try again.");
                }
            } else if (choice == 2) {
                System.out.print("Choose username: ");
                String username = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();
                System.out.print("Confirm Password: ");
                String confirm = scanner.next();
                System.out.print("Initial balance: ");
                double balance = scanner.nextDouble();

                boolean registered = authService.register(username, password, confirm, balance);
                if (registered) {
                    System.out.println("Registration successful. Please log in.");
                } else {
                    System.out.println("Registration failed. Try again.");
                }
            }
        }

        Cart cart = new Cart();
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View and buy products");
            System.out.println("2. Add balance");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    productService.showAllProducts(); // display products with ID and stock
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    Product product = productService.findById(productId);
                    try {
                        cart.addProduct(product, quantity);
                        System.out.println("Added to cart.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Amount to add: ");
                    double amount = scanner.nextDouble();
                    currentUser.addBalance(amount);
                    System.out.println("Balance updated.");
                    break;

                case 3:
                    try {
                        checkoutService.checkout(currentUser, cart, shippingService);
                        cart.clear();
                    } catch (Exception e) {
                        System.out.println("Checkout failed: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }
}

* */