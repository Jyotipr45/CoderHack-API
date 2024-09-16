package com.crio.coderhack;

import com.crio.coderhack.dto.UserDto;
import com.crio.coderhack.entities.User;
import com.crio.coderhack.exception.UserNotFoundException;
import com.crio.coderhack.exception.InvalidScoreException;
import com.crio.coderhack.repositories.UserRepository;
import com.crio.coderhack.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") 
public class CoderHackApplicationTests {

    @Autowired
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository); // Use constructor injection
        userRepository.deleteAll(); // Clean up the database before each test
    }

    @Test
    public void testAddUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setUserName("John Doe");

        UserDto createdUser = userService.addUser(userDto);

        assertNotNull(createdUser);
        assertEquals("1", createdUser.getUserId());
        assertEquals("John Doe", createdUser.getUserName());
        assertEquals(0, createdUser.getScore());
        assertTrue(createdUser.getBadges().isEmpty());
    }

    @Test
    public void testUpdateUserScore() {
        User user = new User();
        user.setUserId("1");
        user.setUserName("John Doe");
        user.setScore(0);
        user.setBadges(Set.of());
        userRepository.save(user);

        UserDto updatedUser = userService.updateUserScore("1", 50);

        assertNotNull(updatedUser);
        assertEquals("1", updatedUser.getUserId());
        assertEquals(50, updatedUser.getScore());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUserId("1");
        user.setUserName("John Doe");
        user.setScore(0);
        user.setBadges(Set.of());
        userRepository.save(user);

        userService.deleteUser("1");

        assertThrows(UserNotFoundException.class, () -> userService.getUserById("1"));
    }


    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUserId("1");
        user.setUserName("John Doe");
        user.setScore(0);
        user.setBadges(Set.of());
        userRepository.save(user);

        var userDto = userService.getUserById("1");

        assertTrue(userDto.isPresent());
        assertEquals("1", userDto.get().getUserId());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUserId("1");
        user1.setUserName("John Doe");
        user1.setScore(10);
        user1.setBadges(Set.of());
        userRepository.save(user1);

        User user2 = new User();
        user2.setUserId("2");
        user2.setUserName("Jane Doe");
        user2.setScore(20);
        user2.setBadges(Set.of());
        userRepository.save(user2);

        var users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getUserId().equals("1")));
        assertTrue(users.stream().anyMatch(u -> u.getUserId().equals("2")));
    }

    @Test
    public void testUpdateUserScore_InvalidScore() {
        User user = new User();
        user.setUserId("1");
        user.setUserName("John Doe");
        user.setScore(0);
        user.setBadges(Set.of());
        userRepository.save(user);

        assertThrows(InvalidScoreException.class, () -> userService.updateUserScore("1", 150));
    }

    @Test
    public void testUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById("nonexistent-id"));
    }    
}
