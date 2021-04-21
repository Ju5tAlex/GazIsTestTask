package service;

import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private String username = "coolguy";
    private User user1 = new User(10, username, "Mike", "Best", (short) 24, "89991234567");
    private User user2 = new User(10, username, "Bill", "Nice", (short) 28, "89991234567");
    private User user3 = new User(10, username, "George", "Pretty", (short) 35, "89991234567");
    @Test
    void findUserByUsername() {
        when(userRepository.findByUsername(username)).thenReturn(user1);
        Assertions.assertEquals(user1, userService.findUserByUsername(username));
        verify(userRepository).findByUsername(username);
    }

    @Test
    void changeUserLastNameWithExistingUser() {
        user1.setLastname("Greatest");
        when(userRepository.findByUsername(username)).thenReturn(user1);
        Assertions.assertEquals(user1, userService.changeUserLastName(username, "Greatest"));
        verify(userRepository, times(2)).findByUsername(username);
        verify(userRepository).updateLastname(username, "Greatest");
    }

    @Test
    void changeUserLastNameWithNonExistingUser() {
        user1.setLastname("Greatest");
        when(userRepository.findByUsername(username)).thenReturn(null);
        Assertions.assertNull(userService.changeUserLastName(username, "Greatest"));
        verify(userRepository).findByUsername(username);
        verify(userRepository, times(0)).updateLastname(username, "Greatest");
    }

    @Test
    void getAllSortedByAge() {
        ArrayList<User> unsortedUsers = new ArrayList<>();
        unsortedUsers.add(user2);
        unsortedUsers.add(user1);
        unsortedUsers.add(user3);

        ArrayList<User> sortedUsers = new ArrayList<>();
        sortedUsers.add(user1);
        sortedUsers.add(user2);
        sortedUsers.add(user3);

        when(userRepository.getAllUsers()).thenReturn(unsortedUsers);
        Assertions.assertEquals(sortedUsers, userService.getAllSortedByAge());
        verify(userRepository).getAllUsers();
    }

    @Test
    void getPhonebook() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user2);
        users.add(user1);
        users.add(user3);

        HashMap<String, String> phoneBook = new HashMap<>();
        phoneBook.put(user1.getLastname(), user1.getPhoneNumber());
        phoneBook.put(user2.getLastname(), user2.getPhoneNumber());
        phoneBook.put(user3.getLastname(), user3.getPhoneNumber());

        when(userRepository.getAllUsers()).thenReturn(users);
        Assertions.assertEquals(phoneBook, userService.getPhonebook());
        verify(userRepository).getAllUsers();
    }
}