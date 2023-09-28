package ex01;

public class UserIdsGenerator {
    private static final UserIdsGenerator INSTANCE = new UserIdsGenerator(1);

    private int currentID;
    
    private UserIdsGenerator(int startID) {
        currentID = startID;
    }
    
    public static UserIdsGenerator getInstance() {
        return INSTANCE;
    }

    public int generateId() {
        return currentID++;
    }
}
