package entities;

public class Customer {
    private int id;
    private String username;
    private String password;
    private String name;
    private double balance;

    public Customer() {
    }

    public Customer(int id, String username, String password, String name, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
    }

    public Customer(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Business logic
    public void pay(double amount) {
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance.");
        balance -= amount;
    }

    public void addFunds(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        balance += amount;
    }
}
