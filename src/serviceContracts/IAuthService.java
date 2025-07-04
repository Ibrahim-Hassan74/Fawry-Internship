package serviceContracts;

import entities.Customer;

public interface IAuthService {
    Customer login(String username, String password);

    boolean register(String username, String password, String confirmPassword, double initialBalance);
}
