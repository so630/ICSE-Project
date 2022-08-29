public class Transactions {
    private static Transaction[] transactions = new Transaction[10];
    private static int current_index = 0;

    /**
     * add transaction to transactions array
     * @param transaction transaction details to be added to transactions array
     */
    public static void add_transaction(Transaction transaction) {
        check_if_full_and_remove();

        transactions[current_index] = transaction;
        current_index++;
    }

    /**
     * check if the transactions array is full and remove the first element and push back the rest by 1 index
     */
    private static void check_if_full_and_remove() {
        if (current_index == 10) {
            transactions[0] = null;
            for (int i = 0; i < transactions.length; i++) {
                if ((i+1) == transactions.length) {
                    transactions[i] = null;
                    current_index--;
                    continue;
                }
                transactions[i] = transactions[i+1];
            }
        }
    }

    /**
     * just a getter function to return the transactions array
     * @return transactions array
     */
    public static Transaction[] get_transactions() {
        return transactions;
    }
}

/**
 *     name        |  description
 * ------------------------------------------------
 *  transactions   | an array containing records of transactions
 *  current_index  | the amount of transactions that is currently present in the transactions array
 *                 |
 */
