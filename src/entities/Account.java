package entities;

public class Account {
    private int id;
    private String username;
    private String password;
    private String name;
    private double balance;

    public Account() {
    }

    public Account(int id, String username, String password, String name, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
