package ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(0, "Wayne", 1000);
        User user2 = new User(1, "Maxwell", 700);
        User user3 = new User(1, "Emil", 0);
        User user4 = new User(1, "Lorraine", 375);
        User user5 = new User(1, "Andrew", 800);


        System.out.println("Printing user data:");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);
        System.out.printf("%80c%n", '-');
        Transaction tran1 = new Transaction(UUID.randomUUID(), 
                user2, 
                user1, 
                Transaction.Type.CREDIT, 
                400);
        Transaction tran2 = new Transaction(UUID.randomUUID(), 
                user5, 
                user4, 
                Transaction.Type.DEBT, 
                100);
        tran1.perform();
        tran2.perform();
        System.out.println(tran1);
        System.out.println(tran2);
        System.out.println("User data after transactions:");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);
    }
}
