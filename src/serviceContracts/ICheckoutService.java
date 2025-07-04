package serviceContracts;

import entities.Cart;
import entities.Customer;

public interface ICheckoutService {
    void checkout(Customer customer, Cart cart);
}
