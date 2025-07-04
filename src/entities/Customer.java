package entities;

public class Customer {
    private int id;
    private String name;
    private double balance;

    public Customer(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void pay(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }

    public void addFunds(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        balance += amount;
    }
}
