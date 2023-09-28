package ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList users = new UsersArrayList();
    private int unverified;

    public TransactionsService addUser(User user) {
        users.addUser(user);
        return this;
    }

    public int getUserBalanceByID(int id) {
        return users.getUserByID(id).getBalance();
    }

    public int getUserBalanceByIndex(int id) {
        return users.getUserByIndex(id).getBalance();
    }

    public TransactionsService performTransaction(
            int recepientID,
            int senderID,
            Transaction.Type type,
            int amount) {
        UUID trID = UUID.randomUUID();
        Transaction transact = new Transaction(
                trID,
                users.getUserByID(recepientID),
                users.getUserByID(senderID),
                type,
                amount);
        transact.perform();
        return this;
    }

    public Transaction[] getUserTransactions(int userID) {
        return users.getUserByID(userID).getTransactionsList().toArray();
    }

    public TransactionsService removeTransaction(int userID, UUID transactionID) {
        User targetUser = users.getUserByID(userID);
        Transaction transaction = targetUser.getTransaction(transactionID);
        transaction = users.getUserByID(transaction.getRecepientID())
                .getTransaction(transactionID);
        if (transaction != null) {
            unverified++;
        } else {
            unverified--;
        }
        targetUser.getTransactionsList()
                .removeTransaction(transactionID);
        return this;
    }

    public Transaction[] checkTransactions() {
        Transaction[] invalidTrs = new Transaction[unverified];
        int ind = 0;
        for (int i = 0; i < users.getUserCount(); i++) {
            Transaction[] targets = users.getUserByIndex(i)
                    .getTransactionsList()
                    .toArray();
            for (int j = 0; j < targets.length; j++) {
                Transaction target = targets[j];
                target = users.getUserByID(target.getRecepientID())
                              .getTransaction(target.getID());
                if (target == null) {
                    invalidTrs[ind++] = targets[j];
                }
            }
        }
        return invalidTrs;
    }
}
