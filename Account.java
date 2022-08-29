/**
 * an object used to create an account.
 */
public class Account {
    int account_number; // account number of account
    int pin; // PIN of account
    String name; // name of person who's account is this
    String address; // address of person
    String type_of_account; // the type of account (Savings or Current)
    double balance_amt; // stores the amount of balance a user has

    /**
     * constructor to initialise the properties of this object
     *
     * @param account_number account number of the account being created
     * @param pin pin of the account being created
     * @param name name of the person who holds the account being created
     * @param address address of the person who holds the account being created
     * @param type_of_account type of this account
     * @param balance_amt the balance amount that is to be in this account
     */
    public Account(int account_number, int pin, String name, String address, String type_of_account, double balance_amt) { // constructor to initialise everything
        this.account_number = account_number;
        this.pin = pin;
        this.name = name;
        this.address = address;
        this.type_of_account = type_of_account;
        this.balance_amt = balance_amt;
    }

    /**
     * a function to withdraw money from an account
     * @param amt amount to be withdrawn
     */
    public void withdraw(double amt) {
        balance_amt -= amt;
        Transaction transaction = new Transaction(account_number, amt, "W");
        Transactions.add_transaction(transaction);
    }

    /**
     * a function to deposit money to an account
     * @param amt amount to be deposited
     */
    public void deposit(double amt) {
        balance_amt += amt;
        Transaction transaction = new Transaction(account_number, amt, "D");
        Transactions.add_transaction(transaction);
        System.out.println("Rs. " + amt + " has been deposited, leaving a balance of " + balance_amt);
    }
}

/**
 *     name        |  description
 * ------------------------------------------------
 * account_number  |  member variable used to store the account number of the concerned account
 * pin             |  member variable used to store the pin of the concerned account
 * name            |  member variable used to store the name of the person who holds the concerned account
 * address         |  member variable used to store the address of the person who holds the concerned account
 * type_of_account |  member variable used to store the type of the concerned account [either savings or current]
 * balance_amt     |  member variable used to store the balance of the concerned account
 * amt (withdraw)  |  parameter of withdraw function denoting the amount to be withdrawn
 * amt (deposit)   |  parameter of deposit function denoting the amount to be deposited
 *                 |
 */
