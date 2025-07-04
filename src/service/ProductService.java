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
        products = JsonHelper.readJson(path, new TypeToken<List<Product>>() {
        }.getType());
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
