package serviceContracts;

import entities.Product;

import java.util.List;

public interface IProductService {
    List<Product> loadProducts();

    Product findById(int id);
}
