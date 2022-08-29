import java.util.Date;

/**
 * an object to hold transaction details.
 */
public class Transaction {
    int account_number; // refers to the account number from which the transaction is occuring
    Date transaction_dt;

    double amount;
    String type_of_transaction;

    /**
     *
     * @param account_number account number of the account this transaction is for
     * @param amount the amount that is being used in this transaction
     * @param type_of_transaction the type of transaction: either Withdraw (W) or Deposit (D)
     */
    public Transaction(int account_number, double amount, String type_of_transaction) {
        this.account_number = account_number;
        this.amount = amount;
        this.type_of_transaction = type_of_transaction;
        this.transaction_dt = new Date();
    }
}

/**
 *     name            |  description
 * ------------------------------------------------
 * account_number      | a variable which refers to the account number from which the transaction has been made
 * amount              | the amount that is being used in this transaction
 * type_of_transaction | the type of transaction: either Withdraw (W) or Deposit (D)
 * transaction_dt      | a date object storing the date that this transaction was held on
 *                     |
 */
