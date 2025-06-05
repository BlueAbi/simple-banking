import java.util.Scanner;

public class BankApp {
    private static final BankService bankService = new BankService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    loginScreen();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void loginScreen() {
        System.out.println("----- Login -----");
        System.out.println("Enter your account number: ");
        int accountNum = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter your PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        //validate the login
        if (bankService.validateLogin(accountNum, pin)) {
            loggedIn(accountNum);
        } else {
            System.out.println("Login failed. Try again.");
        }

    }

    public static void loggedIn(int accountNum) {
        while (true) {
            loginOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    deposit(accountNum);
                    break;
                case 2:
                    withdraw(accountNum);
                    break;
                case 3:
                    transfer(accountNum);
                    break;
                case 4:
                    deleteAcc(accountNum);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("----- Bank Menu -----");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter a PIN number: ");
        int pin = scanner.nextInt();
        System.out.print("Enter initial deposit: ");
        double initBalance = scanner.nextDouble();

        System.out.println("Generating account number...");
        int accountNum = bankService.createAccount(name, pin, initBalance);
        System.out.println("Bank account successfully made. Here is your account number: " + accountNum);
    }

    private static void deposit(int accountNum) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (bankService.deposit(accountNum, amount)) { //calls deposit function in bankService, which takes care of everything
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Deposit failed.");
        }
    }

    private static void withdraw(int accountNum) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (bankService.withdraw(accountNum, amount)) { //calls withdraw function in bankService, which takes care of everything
            System.out.println("Withdraw successful.");
        } else {
            System.out.println("Withdraw failed.");
        }
    }

    private static void transfer(int accountNum) {
        System.out.print("Enter recipient account number: ");
        int recipient = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (bankService.transfer(accountNum, recipient, amount)) { //calls transfer function in bankService, which takes care of everything
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed.");
        }
    }

    private static void deleteAcc(int accountNum) {
        // Optional: Add confirmation logic
        bankService.deleteAccount(accountNum);
        System.out.println("Account deleted.");
    }

}