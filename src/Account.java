import java.util.HashMap;

public class Account {
    private String name;
    private int AccountNum; // act #
    private int pin; // pin #
    private double balance; // checking balance
    private boolean lock; // if account is locked

    public Account(String name, int AccountNum, int pin, double balance, boolean lock) { //constructor
        this.name = name;
        this.AccountNum = AccountNum;
        this.pin = pin;
        this.balance = balance;
        this.lock = lock;
    }

    // Getter methods
    public String getName() { return name; }
    public int getAccountNum() { return AccountNum; }
    public int getPin() { return pin; }
    public double getBalance() { return balance; }
    public boolean isLocked() { return lock; }

    // Method to update balance when change in balance is initiated such as deposit
    public void setBalance(double balance) {
        this.balance = balance;
    }
}