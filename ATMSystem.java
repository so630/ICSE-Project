import java.util.Locale;
import java.util.Scanner;

public class ATMSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static Account account;

    public static void main(String[] args) {
        System.out.println("Welcome to ATM System, are you already registered? (yes or no)");
        char choice = scanner.nextLine().toLowerCase(Locale.ROOT).charAt(0);

        switch (choice) {
            case 'y':
                login();
                break;

            case 'n':
                register();
                break;
        }
    }

    /**
     * a utility function to login user if they are already registered
     */
    private static void login() {
        System.out.println("commencing login process....");
        System.out.print("enter account number: ");
        int account_number = scanner.nextInt();
        System.out.println("\nenter pin: ");
        int pin = scanner.nextInt();
        account = Accounts.search_for_account(account_number, pin);
        if (account == null) {
            System.out.println("pin or account number was incorrect.");
            System.out.println("exiting...");
        } else {
            System.out.println("login successful");
            transaction();
        }
    }

    /**
     * a utility function to register user
     */
    private static void register() {
        System.out.println("commencing registering process....");
        int account_number;
        System.out.print("enter account number: ");
        account_number = scanner.nextInt();
        System.out.print("\nenter pin: ");
        int pin = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\nenter name: ");
        String name = scanner.nextLine();
        System.out.print("\nenter address: ");
        String address = scanner.nextLine();
        String type_of_account;
        while (true) {
            System.out.print("\nenter the type of account (savings or current): ");
            type_of_account = scanner.nextLine();
            if (type_of_account.equals("savings") || type_of_account.equals("current")) {
                break;
            } else {
                System.out.print("\nthe type of account must be savings or current, not anything else. enter again");
            }
        }
        double balance_amt;
        while (true) {
            System.out.println("\nenter the balance amount: ");
            balance_amt = scanner.nextDouble();

            if (type_of_account.equals("savings")) {
                if (balance_amt >= 5000d) {
                    break;
                } else {
                    System.out.println("balance amount must be a minimum of Rs. 5,000 in case of savings account. enter again...");
                }
            } else {
                if (balance_amt >= 10000d) {
                    break;
                } else {
                    System.out.println("balance amount must be a minimum of Rs. 10,000 in case of current account. enter again...");
                }
            }
        }

        account = new Account(account_number, pin, name, address, type_of_account, balance_amt);
        Accounts.add_account(account); // adding account to array
        System.out.println("register successful");
        System.out.println("\n\n");
        transaction();
    }

    /**
     * a utility function to print all transactions and process subsequent user input
     */
    private static void transaction() {
        scanner.nextLine();
        boolean quit = false;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if (account == null) {
            System.exit(0);
        }
        while (!quit) {
            print_transactions();

            char c = scanner.nextLine().charAt(0);
            switch (c) {
                case 'a':
                    check_balance();
                    break;

                case 'b':
                    withdraw();
                    break;

                case 'c':
                    deposit();
                    break;

                case 'd':
                    print_last_3_transactions();
                    break;

                case 'e':
                    quit = true;
                    break;

                default:
                    System.out.println("incorrect option, try again...");
                    break;
            }
        }
    }

    /**
     * a utility function to print the last 3 transactions of user
     *
     */
    private static void print_last_3_transactions() {
        System.out.println("\n\nLast Three Transactions:");
        int c = 0;
        for (int i = Transactions.get_transactions().length-1; i >= 0; i--) {
            if (Transactions.get_transactions()[i] == null) {
                continue;
            }

            if (Transactions.get_transactions()[i].account_number == account.account_number) {
                System.out.println("- " + (Transactions.get_transactions()[i].type_of_transaction.equals("W") ? "withdraw " : "deposit ") + "of Rs. " + Transactions.get_transactions()[i].amount + " on " + (Transactions.get_transactions()[i].transaction_dt.toLocaleString()));
                c++;
                if (c == 3) {
                    break;
                }
            }
        }
    }

    /**
     * utility function to deposit amount to account
     */
    private static void deposit() {
        if (account == null) {
            System.exit(0);
        }
        double amt;
        while (true) {
            System.out.print("enter the amount you wish to deposit: ");
            amt = scanner.nextDouble();
            if (amt < 0) {
                System.out.println("amount must be positive, enter again...");
            } else {
                break;
            }
        }
        account.deposit(amt);
        scanner.nextLine();
    }

    /**
     * a utility function to withdraw money from an account
     */
    private static void withdraw() {
        if (account == null) {
            System.exit(0);
        }
        double amt;
        while (true) {
            System.out.print("enter the amount you wish to withdraw: ");
            amt = scanner.nextDouble();
            if (account.type_of_account.equals("savings")) {
                if (amt <= 25000d && amt <= account.balance_amt && account.balance_amt-amt >= 5000d) {
                    account.withdraw(amt);
                    System.out.println("Rs. " + amt + " has been withdrawn, leaving a balance of " + account.balance_amt);
                    scanner.nextLine();
                    break;
                } else if (amt > account.balance_amt) {
                    System.out.println("your balance amount is " + account.balance_amt + ", which is greater than the amount you wish to withdraw (" + amt + "). enter again");
                } else if (amt > 25000d) {
                    System.out.println("the amount you withdraw must be smaller than Rs. 25,000 in case of savings account. enter again");
                } else if (account.balance_amt-amt <= 5000d) {
                    System.out.println("you must leave a minimum of Rs. 5,000 in your account, but you are leaving a balance less than it. enter again");
                }
            } else {
                if (amt <= 50000d && amt <= account.balance_amt && account.balance_amt-amt >= 10000d) {
                    account.balance_amt -= amt;
                    System.out.println("Rs. " + amt + " has been withdrawn, leaving a balance of " + account.balance_amt);
                    account.withdraw(amt);
                    scanner.nextLine();
                    break;
                } else if (amt > account.balance_amt) {
                    System.out.println("your balance amount is " + account.balance_amt + ", which is greater than the amount you wish to withdraw (" + amt + "). enter again");
                } else if (amt > 50000d) {
                    System.out.println("the amount you withdraw must be smaller than Rs. 50,000 in the case of current account. enter again");
                } else if (account.balance_amt-amt <= 10000d) {
                    System.out.println("you must leave a minimum of Rs. 10,000 in your account, but you are leaving a balance less than it. enter again");
                }
            }
        }
    }

    /**
     * utility function to check balance of account
     */
    private static void check_balance() {
        if (account == null) { // should not happen but if it does
            System.out.println("account does not exist, exiting...");
            System.exit(0); // just exit
        }
        System.out.println("your balance is " + account.balance_amt);
    }

    /**
     * utility function to print the transactions to console
     */
    private static void print_transactions() {
        System.out.println("\n");
        System.out.println("Transactions Menu: ");
        System.out.println("a) Check balance");
        System.out.println("b) Withdraw Cash");
        System.out.println("c) Deposit Cash");
        System.out.println("d) Access last three transactions");
        System.out.println("e) quit");
    }
}

/**
 *     name        |  description
 * ------------------------------------------------
 * account_number  | local variable of login function to accept account number of account to be logged into
 * pin             | local variable of login function to accept pin of account to be logged into
 * account_number  | local variable of register function to accept account number of account to be registered
 * pin             | local variable of register function to accept pin of account to be registered
 * name            | local variable of register function to accept name of person whose account is to be registered
 * address         | local variable of register function to accept address of person whose account is to be registered
 * type_of_account | local variable of register function to accept the type of account to be registered
 * balance_amt     | local variable of register function to accept initial balance of account to be registered
 * account         | global static variable of ATMSystem class which stores an instance of the account class in itself in order to denote the account which is currently logged in
 * quit            | a local variable of transaction function which is used to denote whether the program is to be quit yet
 * amt             | a local variable of withdraw function which is used to input the amount to be withdrawn
 * amt             | a local variable of deposit function which is used to input the amount to be deposited
 * c               | a local variable of print_last_3_transactions function which is used as a simple counter variable
 *                 |
 */