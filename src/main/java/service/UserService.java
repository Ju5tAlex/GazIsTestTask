package service;

import entity.User;
import repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {
    private UserRepository repository = new UserRepository();

    public User findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User changeUserLastName(String username, String lastname) {
        User user = findUserByUsername(username);
        if (user != null) {
            repository.updateLastname(username, lastname);
            user = findUserByUsername(username);
        }
        return user;
    }

    public List<User> getAllSortedByAge() {
        List<User> allUsers = repository.getAllUsers();
        allUsers.sort(Comparator.comparing(User::getAge));
        return allUsers;
    }

    public Map<String, String> getPhonebook() {
        List<User> allUsers = repository.getAllUsers();
        return allUsers.stream().collect(Collectors.toMap(User::getLastname, User::getPhoneNumber, (phone1, phone2) -> phone1 + ", " + phone2));
    }
}
