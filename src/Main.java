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