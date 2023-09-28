package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Wayne", 1000);
        User user2 = new User("Maxwell", 700);
        User user3 = new User("Emil", 0);
        User user4 = new User("Lorraine", 375);
        User user5 = new User("Andrew", 800);

        System.out.println("Printing user data:");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);
    }
}
