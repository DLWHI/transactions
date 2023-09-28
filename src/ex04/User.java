package ex04;

import java.util.UUID;

public class User {
    private static final String toStringFormat = "User %s(id = %d)" +
            "| Balance: %d";

    private int id;
    private String name;
    private int balance;
    private TransactionsList transactions = new TransactionsLinkedList();

    public User(String name, int initBalance) {
        if (balance < 0) {
            throw new IllegalTransactionException(
                "User with negative amounts");
        }
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = initBalance;
    }

    public int getID() {
        return id;
    }

    int getBalance() {
        return balance;
    }

    public TransactionsList getTransactionsList() {
        return transactions;
    }
    
    public Transaction getTransaction(UUID id) {
        Transaction[] tranArr = transactions.toArray();
        for (int i = 0; i < tranArr.length; i++) {
            if (tranArr[i].equals(id)) {
                return tranArr[i];
            }
        }
        return null;
    }

    public User changeBalance(int amount) {
        balance += amount;
        if (balance < 0) {
            throw new IllegalTransactionException(
                "Insufficient money amount");
        }
        return this;
    }

    public String toString() {
        return String.format(toStringFormat, name, id, balance);
    }
}
