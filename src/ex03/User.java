package ex03;

public class User {
    private static final String toStringFormat = "User %s(id = %d) | Balance: %d";
    private static final String negBalanceMsgFormat = "%s(id = %d) " +
            "has negative balance!";

    private int id;
    private String name;
    private int balance;
    private TransactionsList transactions = new TransactionsLinkedList();

    public User(String name, int initBalance) {
        if (balance < 0) {
            System.err.printf(negBalanceMsgFormat, name, id);
            System.exit(-1);
        }
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = initBalance;
    }

    public int getID() {
        return id;
    }

    public TransactionsList getTransactionsList() {
        return transactions;
    }

    public User changeBalance(int amount) {
        balance += amount;
        if (balance < 0) {
            System.err.printf(negBalanceMsgFormat, name, id);
            System.exit(-1);
        }
        return this;
    }

    public String toString() {
        return String.format(toStringFormat, name, id, balance);
    }
}
