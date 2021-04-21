package repository;

import entity.User;
import util.ConnectionFromBd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String SQL_GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username LIKE ?";
    private static final String SQL_UPDATE_USERS_LASTNAME = "UPDATE users SET lastname = ? WHERE username LIKE ?";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users";

    public User findByUsername(String username) {
        User user = null;
        try {
            PreparedStatement statement = ConnectionFromBd.getConnection().prepareStatement(SQL_GET_USER_BY_USERNAME);
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                user = new User(set.getLong("id"), set.getString("username"),
                        set.getString("firstname"), set.getString("lastname"),
                        set.getShort("age"), set.getString("phonenumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateLastname(String username, String lastname) {
        try {
            PreparedStatement statement = ConnectionFromBd.getConnection().prepareStatement(SQL_UPDATE_USERS_LASTNAME);
            statement.setString(1, lastname);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = ConnectionFromBd.getConnection().prepareStatement(SQL_GET_ALL_USERS);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                users.add(new User(set.getLong("id"), set.getString("username"),
                        set.getString("firstname"), set.getString("lastname"),
                        set.getShort("age"), set.getString("phonenumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
