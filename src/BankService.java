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
        int num = 0; //initialize flag which will be true once unique number is found
        boolean unique = false;

        while (!(unique)) {
            // Generate a random 8-digit number
            num = (int) (Math.random() * 100000000);  // Random number between 0 and 99999999

            // Check if the number is already used
            if (accounts.containsKey(num)) {
                unique = true;  // If it's unique, exit the loop
            }
        }
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
            double newBalanceS = accR.getBalance() - amount;
            double newBalanceR = accR.getBalance() + amount;
            return true;
        } else {return false;}
    }

    public boolean validateLogin (int accountNum, int pin) {

    }
}