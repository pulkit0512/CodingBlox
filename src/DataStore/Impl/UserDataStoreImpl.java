package DataStore.Impl;

import DataObjects.User;
import DataStore.UserDataStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataStoreImpl implements UserDataStore {
    private static final Map<String, User> userDB = new HashMap<>();

    @Override
    public void addUser(User user) {
        userDB.put(user.getUserId(), user);
    }

    @Override
    public User getUser(String userId) {
        return userDB.get(userId);
    }

    @Override
    public void updateUser(User user) {
        addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userDB.values());
    }
}
