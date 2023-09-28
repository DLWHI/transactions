package ex05;

public class UserIdsGenerator {
    private static UserIdsGenerator instance = new UserIdsGenerator(1);

    private int currentID;
    
    private UserIdsGenerator(int startID) {
        currentID = startID;
    }
    
    public static UserIdsGenerator getInstance() {
        return instance;
    }

    public int generateId() {
        return currentID++;
    }
}
