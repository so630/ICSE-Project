public class Accounts {
    private static Account[] accounts = new Account[2];
    private static int current_index = 0;

    /**
     * function to add account to accounts array
     * @param account account of person to be added to array 'accounts'
     */
    public static void add_account(Account account) {
        if (!is_full()) {
            accounts[current_index] = account;
            current_index++;
        }
    }

    /**
     * function to search for account
     * @param account_number account number of account to be searched for
     * @param pin pin of account to be searched for
     * @return account of person if found, else null
     */
    public static Account search_for_account(int account_number, int pin) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) continue;
            if (accounts[i].account_number == account_number && accounts[i].pin == pin) {
                return accounts[i];
            }
        }

        return null;
    }

    /**
     * check if accounts array is full
     * @return boolean indicating if accounts array is full
     */
    public static boolean is_full() {
        return current_index == 2;
    }
}

/**
 *     name        |  description
 * ------------------------------------------------
 *  accounts       | an array to store the accounts of users
 *  current_index  | variable to store the amount of accounts actually stored in the array
 *  account        | parameter of add_account function which is an account to be added to the accounts array
 *  account_number | parameter of search_for_account function which denotes the account number of the account to be searched
 *  pin            | parameter of search_for_account function which denotes the pin of the account to be searched
 *                 |
 * */
