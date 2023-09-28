package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList userList = new UsersArrayList();
        userList.addUser(new User("Wayne", 1000));
        userList.addUser(new User("Maxwell", 700));
        userList.addUser(new User("Emil", 0));
        userList.addUser(new User("Lorraine", 375));
        userList.addUser(new User("Andrew", 800));

        System.out.printf("Current UserArrayList capacity: %d%n",
                userList.getCapacity());

        userList.addUser(new User("Steve", 1000));
        userList.addUser(new User("Mirina", 1000));
        userList.addUser(new User("Stirlitz", 1000000));
        userList.addUser(new User("Luke", 8080));
        userList.addUser(new User("Beatris", 9999));
        userList.addUser(new User("Jacob", 0));
        System.out.println("Inserted 6 more users");
        System.out.printf("Current UserArrayList capacity: %d%n%n",
                userList.getCapacity());

        System.out.printf("Asking user with id= %d: %s%n", 8,
                userList.getUserByID(8));
        System.out.printf("Asking user with id= %d: %s%n", 3,
                userList.getUserByID(3));
        System.out.printf("Asking user with id= %d: %s%n%n", 4,
                userList.getUserByID(4));
        
        // Throws
        // System.out.printf("Asking user with id= %d: %s%n", 100,
        //         userList.getUserByID(100));

        System.out.println("All users in UserArrayList:");
        for (int i = 0; i < userList.getUserCount(); i++) {
            System.out.println(userList.getUserByIndex(i));
        }
        
    }
}
