package service;

import entities.Account;
import entities.Customer;
import serviceContracts.IAuthService;
import utils.JsonHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.reflect.TypeToken;

public class AuthService implements IAuthService {

    private final String filePath = "src/data/accounts.json";
    private final Type accountListType = new TypeToken<List<Account>>() {
    }.getType();

    @Override
    public Customer login(String username, String password) {
        List<Account> accounts = JsonHelper.readJson(filePath, accountListType);
        if (accounts == null) throw new RuntimeException("Couldn't load accounts");

        Optional<Account> match = accounts.stream()
                .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
                .findFirst();

        if (match.isEmpty())
            throw new RuntimeException("Invalid username or password.");

        Account acc = match.get();
        return new Customer(acc.getId(), acc.getName(), acc.getBalance());
    }

    @Override
    public boolean register(String username, String password, String confirmPassword, double initialBalance) {
        if (!password.equals(confirmPassword))
            throw new RuntimeException("Passwords do not match.");

        List<Account> accounts = JsonHelper.readJson(filePath, accountListType);
        if (accounts == null) accounts = new ArrayList<>();

        boolean exists = accounts.stream().anyMatch(a -> a.getUsername().equals(username));
        if (exists)
            throw new RuntimeException("Username already exists.");

        int nextId = accounts.stream().mapToInt(Account::getId).max().orElse(0) + 1;

        Account newAcc = new Account(nextId, username, password, username, initialBalance);
        accounts.add(newAcc);

        JsonHelper.writeJson(filePath, accounts);
        return true;
    }
}
