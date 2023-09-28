package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Wayne", 1000);
        User user2 = new User("Maxwell", 700);

        TransactionsLinkedList transactions = new TransactionsLinkedList();
        UUID killedTransactionID = UUID.randomUUID();

        transactions.addTransaction(new Transaction(
            killedTransactionID,
                user1,
                user2, 
                Transaction.Type.DEBT, 
                150));
        transactions.addTransaction(new Transaction(
            UUID.randomUUID(),
                user1,
                user2, 
                Transaction.Type.CREDIT, 
                150));
        transactions.addTransaction(new Transaction(
            UUID.randomUUID(),
                user2,
                user1, 
                Transaction.Type.CREDIT, 
                150));
        System.out.println("Added 3 transactions:");
        Transaction[] transactionArray = transactions.toArray();
        for (int i = 0; i < transactionArray.length; i++) {
            System.out.println(transactionArray[i]);
        }

        transactions.removeTransaction(killedTransactionID);
        System.out.printf("%n%nRemoved 1 transaction:%n");
        transactionArray = transactions.toArray();
        for (int i = 0; i < transactionArray.length; i++) {
            System.out.println(transactionArray[i]);
        }
    }
}
