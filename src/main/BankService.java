import java.util.HashMap;

public class BankService {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public int createAccount(String name, int pin, double balance) {
        int accountNum = newAccountNum();
        Account newAccount = new Account(name, accountNum, pin, balance, false);
        accounts.put(accountNum, newAccount);
        return accountNum;
    }

    private int newAccountNum() {
        int num;
        do {
            num = (int) (Math.random() * 100_000_000); // Generate 8-digit number
        } while (accounts.containsKey(num)); // Keep looping until it's NOT in the map
        return num;
    }

    public boolean deposit (int AccountNum, double amount) {
        Account account = accounts.get(AccountNum);
        if (account != null && !account.isLocked()) {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            return true;
        } else {return false;}
    }

    public boolean withdraw (int AccountNum, double amount) {
        Account account = accounts.get(AccountNum);
        if (account != null && (account.getBalance() >= amount) && !account.isLocked()) {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            return true;
        } else {return false;}
    }

    public boolean transfer (int sender, int recipient, double amount) {
        Account accS = accounts.get(sender);
        Account accR = accounts.get(recipient);
        if (accS != null && accR != null && !accS.isLocked() && (accS.getBalance() >= amount)) {
            accS.setBalance(accS.getBalance() - amount); // Deducts from sender
            accR.setBalance(accR.getBalance() + amount); // Adds to recipient
            return true;
        } else {return false;}
    }

    public boolean validateLogin (int accountNum, int pin) {
        Account account = accounts.get(accountNum);
        return account != null && account.getPin() == pin && !account.isLocked();
    }

    public void deleteAccount(int accountNum) {
        accounts.remove(accountNum);
    }
}