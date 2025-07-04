package service;

import com.google.gson.reflect.TypeToken;
import entities.Customer;
import serviceContracts.ICustomerService;
import utils.JsonHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {

    private List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> loadCustomers(String filePath) {
        Type listType = new TypeToken<List<Customer>>() {
        }.getType();
        List<Customer> loaded = JsonHelper.readJson(filePath, listType);
        if (loaded != null) {
            this.customers = loaded;
        }
        return customers;
    }

    @Override
    public void saveCustomers(String filePath, List<Customer> customers) {
        JsonHelper.writeJson(filePath, customers);
    }

    @Override
    public Customer findById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Customer findByUsername(String username) {
        return customers.stream()
                .filter(c -> c.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}
