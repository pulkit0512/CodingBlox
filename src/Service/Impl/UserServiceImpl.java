package Service.Impl;

import DataObjects.User;
import DataStore.Impl.UserDataStoreImpl;
import DataStore.UserDataStore;
import Service.UserService;

import java.util.Comparator;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDataStore userDataStore = new UserDataStoreImpl();

    @Override
    public void createUser(String userName) {
        User user = new User();
        user.setName(userName);
        user.setScore(1500);
        user.setUserId(user.getUserId()+userName);

        userDataStore.addUser(user);
        System.out.println("User with user name: " + user.getUserId() + " added successfully.");
    }

    @Override
    public void getLeaderBoard(String order) {
        List<User> userList = userDataStore.getAllUsers();

        if (order.equalsIgnoreCase("DESC")) {
            userList.sort(Comparator.comparingInt(User::getScore).reversed());
        } else {
            userList.sort(Comparator.comparingInt(User::getScore));
        }

        userList.forEach(user -> System.out.println(user.getUserId() + " " + user.getScore()));
    }
}
