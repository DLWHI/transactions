package ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();
        boolean dev = false;
        if (args.length > 0) {
            if (args[0].equals("--profile=dev")) {
                dev = true;
            } else {
                dev = false;
            }
        }
        Menu app = new Menu(service,
                new Scanner(System.in), 
                System.out,
                dev);
        app.exec();
    }
}
