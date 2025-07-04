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

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
