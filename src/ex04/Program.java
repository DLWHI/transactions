package ex04;

public class Program {
    public static void main(String[] args) {
        TransactionsService bankService = new TransactionsService();
        bankService.addUser(new User("Wayne", 122784));
        bankService.addUser(new User("Maxwell", 322854));
        bankService.addUser(new User("Emil", 0));
        bankService.addUser(new User("Lorraine", 1750));
        bankService.addUser(new User("Andrew", 80000));

        // Wayne gives to Maxwell 20K that he got from the deal
        bankService.performTransaction(2, 1, Transaction.Type.CREDIT, 20000);
        // Lorraine asks Andrew 4250 so she could have a haircut
        bankService.performTransaction(5, 4, Transaction.Type.DEBT, 4250);
        // Maxwell gives 5k to Andrew to wash money 
        bankService.performTransaction(5, 2, Transaction.Type.CREDIT, 5000);
        // Then 7125
        bankService.performTransaction(5, 2, Transaction.Type.CREDIT, 7125);
        // Andrew gives 4k to someone Emil to wash
        bankService.performTransaction(3, 5, Transaction.Type.CREDIT, 4000);
        // Andrew asks more from Maxwell. He gives 4k
        bankService.performTransaction(2, 5, Transaction.Type.DEBT, 4000);
        // Emil gives back 2250 to Maxwell
        bankService.performTransaction(2, 3, Transaction.Type.CREDIT, 2250);
        // Maxwell gives last 3875 to Andrew. Maxwell has no more money from deal
        bankService.performTransaction(5, 2, Transaction.Type.CREDIT, 3875);
        // At this point Andrew washed 4k. Emil asks for the remaining 15k
        bankService.performTransaction(5, 3, Transaction.Type.DEBT, 15000);
        // Emil gives remaining 16750 to Maxwell. Now the money is clean
        bankService.performTransaction(2, 3, Transaction.Type.CREDIT, 16750);
        // Now Andrew got +1k for washing the money
        // Maxwell got 19k from deal (total 341854)
        // Andrew gives 2k to Lorraine for her firly needs
        bankService.performTransaction(4, 5, Transaction.Type.CREDIT, 2000);
        // Maxwell washes 200k via Wayne
        bankService.performTransaction(1, 2, Transaction.Type.CREDIT, 200000);
        bankService.performTransaction(1, 2, Transaction.Type.DEBT, 200000);
        
        Transaction[] transactMaxwell = bankService.getUserTransactions(2);
        System.out.println("Maxwell has following transactions:");
        for (int i = 0; i < transactMaxwell.length; i++) {
            System.out.println(transactMaxwell[i]);
        }
        System.out.println("");
        System.out.println("Emil has following transactions");
        Transaction[] transactEmil = bankService.getUserTransactions(3);
        for (int i = 0; i < transactEmil.length; i++) {
            System.out.println(transactEmil[i]);
        }

        // Maxwell removes his 200k transaction to leave no traces
        bankService.removeTransaction(2, transactMaxwell[7].getID());
        bankService.removeTransaction(2, transactMaxwell[8].getID());
        System.out.println("");
        System.out.println("Cheking transations...");
        System.out.println("Found unverified transactions:");
        Transaction[] invalid = bankService.checkTransactions();
        for (int i = 0; i < invalid.length; i++) {
            System.out.println(invalid[i]);
        }
    }
}
