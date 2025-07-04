package serviceContracts;

import entities.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> loadCustomers(String filePath);

    void saveCustomers(String filePath, List<Customer> customers);

    Customer findById(int id);

    Customer findByUsername(String username);
}
