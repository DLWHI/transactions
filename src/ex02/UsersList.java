package ex02;

interface UsersList {
    void addUser(User user);

    User getUserByID(int id);

    User getUserByIndex(int ind);

    int getUserCount();
}
