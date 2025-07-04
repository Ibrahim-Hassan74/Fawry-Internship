package service;

import entities.Product;
import serviceContracts.IProductService;
import utils.JsonHelper;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private List<Product> products = new ArrayList<>();
    private String path = "src/data/products.json";

    @Override
    public List<Product> loadProducts() {
//        if (products.isEmpty()) {
        List<Product> loaded = JsonHelper.readJson(path, new TypeToken<List<Product>>() {
        }.getType());
        if (loaded != null) {
            products = loaded.stream()
                    .filter(p -> p.getQuantity() > 0)
                    .toList();
        }
//        }
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveProducts() {
        List<Product> availableProducts = products.stream()
                .filter(p -> p.getQuantity() > 0)
                .toList();

        JsonHelper.writeJson(path, availableProducts);
    }

}
