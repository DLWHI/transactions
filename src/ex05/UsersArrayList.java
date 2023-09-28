package ex05;

public class UsersArrayList implements UsersList {
    private static final int START_CAP = 10;
    private int size = 0;
    private User[] userArray;

    public UsersArrayList() {
        userArray = new User[START_CAP];
    }

    public UsersArrayList(int capacity) {
        userArray = new User[capacity];
    }

    public void addUser(User user) {
        if (size == userArray.length) {
            resizeBuffer();
        }
        userArray[size] = user;
        size++;
    }

    private void resizeBuffer() {
        User[] buffer = new User[userArray.length + userArray.length/2];
        for (int i = 0; i < userArray.length; i++)
            buffer[i] = userArray[i];
        userArray = buffer;
    }

    public User getUserByID(int id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (userArray[i].getID() == id)
                return userArray[i];
        }
        throw new UserNotFoundException("Accessing non existent user id");
    }

    public User getUserByIndex(int ind) {
        return userArray[ind];
    }

    public int getUserCount() {
        return size;
    }

    public int getCapacity() {
        return userArray.length;
    }
}
