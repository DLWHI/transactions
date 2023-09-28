package ex05;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static String menuText = "1. Add a user%n" +
            "2. View user balances%n" +
            "3. Perform a transfer%n" +
            "4. View all transactions for a specific user%n" +
            "5. Finish execution%n-> ";
    private static String menuTextDev = "1. Add a user%n" +
            "2. View user balances%n" +
            "3. Perform a transfer%n" +
            "4. View all transactions for a specific user%n" +
            "5. DEV - remove a transfer by ID%n" +
            "6. DEV - check transfer validity%n" +
            "7. Finish execution%n-> ";
    private static String inputMismMsg = "Invalid balance or id input";
    private static String userNotFoundMsg = "User with id = %d does not exist%n";
    private static String unverifMsg = "%s(id = %d) has an unacknowledged" +
            "transfer id = %s from %s(id = %d) for %d%n";

    private TransactionsService service;
    private Scanner inStream;
    private PrintStream outStream;
    private String menu = menuText;
    private int addUserButton = 1;
    private int viewBalanceButton = 2;
    private int transferButton = 3;
    private int viewTransactsButton = 4;
    private int removeTransactButton;
    private int checkValidButton;
    private int exitButton = 5;

    public Menu(TransactionsService serviceRef,
            Scanner input,
            PrintStream output,
            boolean isDev) {
        if (isDev) {
            removeTransactButton = 5;
            checkValidButton = 6;
            exitButton = 7;
            menu = menuTextDev;
        }
        service = serviceRef;
        inStream = input;
        outStream = output;
    }

    public void exec() {
        int command = 0;
        do {
            try {
                outStream.printf(menu);
                command = inStream.nextInt();
                if (command == addUserButton) {
                    addUser();
                } else if (command == viewBalanceButton) {
                    getBalance();
                } else if (command == transferButton) {
                    makeTransfer();
                } else if (command == viewTransactsButton) {
                    getTransactions();
                } else if (command == removeTransactButton && checkValidButton != 0) {
                    removeTransaction();
                } else if (command == checkValidButton && checkValidButton != 0) {
                    checkTransactions();
                } else if (command != exitButton) {
                    outStream.println("Unknown command");
                }
                outStream.println("");
            } catch (InputMismatchException e) {
                flushIn();
            }
        } while (command != exitButton);
    }

    private void addUser() {
        try {
            outStream.println("Enter a user name and a balance");
            User newUser = new User(inStream.next(), inStream.nextInt());
            service.addUser(newUser);
            outStream.printf("User with id = %d is added%n", newUser.getID());
        } catch (IllegalTransactionException e) {
            outStream.println("User cannot have negative balance");
        } catch (InputMismatchException e) {
            flushIn();
        }
    }

    private void getBalance() {
        int id = 0;
        try {
            outStream.println("Enter a user ID");
            id = inStream.nextInt();
            int balance = service.getUserBalanceByID(id);
            String name = service.getUserNameByID(id);
            outStream.printf("%s - %d%n", name, balance);
        } catch (UserNotFoundException e) {
            outStream.printf(userNotFoundMsg, id);
        } catch (InputMismatchException e) {
            flushIn();
        }
    }

    private void makeTransfer() {
        int id = 0;
        try {
            outStream.println("Enter a sender ID, a recipient ID," +
                    "and a transfer amount");
            service.performTransaction(
                    inStream.nextInt(),
                    inStream.nextInt(),
                    Transaction.Type.CREDIT,
                    inStream.nextInt());
            outStream.println("The transfer is completed");
        } catch (UserNotFoundException e) {
            outStream.printf(userNotFoundMsg, id);
        } catch (IllegalTransactionException e) {
            outStream.printf("Insufficient money amount%n", id);
        } catch (InputMismatchException e) {
            flushIn();
        }
    }

    private void getTransactions() {
        int id = 0;
        try {
            outStream.println("Enter a user ID");
            id = inStream.nextInt();
            Transaction[] transactions = service.getUserTransactions(id);
            for (int i = 0; i < transactions.length; i++) {
                outStream.printf(
                        "To %s(id = %d) %d with id = %s%n",
                        service.getUserNameByID(transactions[i].getRecepientID()),
                        transactions[i].getRecepientID(),
                        -transactions[i].getAmount(),
                        transactions[i].getID());
            }
        } catch (UserNotFoundException e) {
            outStream.printf(userNotFoundMsg, id);
        } catch (InputMismatchException e) {
            flushIn();
        }
    }

    private void removeTransaction() {
        int id = 0;
        try {
            outStream.println("Enter a user ID and a transfer ID");
            id = inStream.nextInt();
            String uuid = inStream.nextLine();
            Transaction removed = service.removeTransaction(
                    id,
                    UUID.fromString(uuid.trim()));
            outStream.printf(
                    "Transfer To %s(id = %d) %d removed%n",
                    service.getUserNameByID(removed.getRecepientID()),
                    removed.getRecepientID(),
                    removed.getAmount());
        } catch (UserNotFoundException e) {
            outStream.printf(userNotFoundMsg, id);
        } catch (TransactionNotFoundException e) {
            outStream.printf("No such transaction by user %d", id);
        } catch (InputMismatchException e) {
            flushIn();
        }
    }

    private void checkTransactions() {
        outStream.println("Check results");
        Transaction[] unverified = service.checkTransactions();
        if (unverified.length == 0) {
            outStream.println("All transfers are valid");
        } else {
            for (int i = 0; i < unverified.length; i++) {
                int amount = unverified[i].getAmount();
                amount = (amount < 0) ? -amount : amount;
                outStream.printf(
                    unverifMsg,
                    service.getUserNameByID(unverified[i].getSenderID()),
                    unverified[i].getSenderID(),
                    unverified[i].getID(),
                    service.getUserNameByID(unverified[i].getRecepientID()),
                    unverified[i].getRecepientID(),
                    amount);
            }
        }
    }

    private void flushIn() {
        outStream.println(inputMismMsg);
        outStream.println("");
        inStream.nextLine();
    }
}
