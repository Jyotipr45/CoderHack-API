package com.crio.coderhack.services;

import com.crio.coderhack.dto.UserDto;
import com.crio.coderhack.entities.User;
import com.crio.coderhack.exception.UserNotFoundException;
import com.crio.coderhack.exception.InvalidScoreException;
import com.crio.coderhack.exception.InvalidBadgeSetException;
import com.crio.coderhack.repositories.UserRepository;
import com.crio.coderhack.util.BadgeEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users with valid badge sets, sorted by score descending
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(this::isBadgeSetValid)
                .map(this::convertToDto)
                .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                .collect(Collectors.toList());
    }

    // Get a user by ID if the badge set is valid
    @Override
    public Optional<UserDto> getUserById(String userId) {
        return userRepository.findById(userId)
                .filter(this::isBadgeSetValid)
                .map(this::convertToDto);
    }

    // Add a new user with initial score and empty badges
    @Override
    public UserDto addUser(UserDto userDto) {
        validateNewUser(userDto);
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setScore(0);
        user.setBadges(Set.of());
        return convertToDto(userRepository.save(user));
    }

    // Update user's score and badges
    @Override
    public UserDto updateUserScore(String userId, int score) {
        validateScore(score);
        User user = findUserById(userId);
        user.setScore(score);
        user.updateBadges(score);
        validateBadgeSet(user);
        return convertToDto(userRepository.save(user));
    }

    // Delete a user by ID
    @Override
    public void deleteUser(String userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    // Find user by ID, throw exception if not found
    private User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    // Validate if score is within the acceptable range
    private void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new InvalidScoreException("Score must be between 0 and 100.");
        }
    }

    // Validate that new user data is complete
    private void validateNewUser(UserDto userDto) {
        if (userDto.getUserId() == null || userDto.getUserName() == null) {
            throw new IllegalArgumentException("User ID and Username must be provided.");
        }
    }

    // Validate that the user has a valid number of badges
    private void validateBadgeSet(User user) {
        if (!user.isValidBadgeSet()) {
            throw new InvalidBadgeSetException("Badge set contains more than the allowed number of badges.");
        }
    }

    // Check if the user's badge set is valid
    private boolean isBadgeSetValid(User user) {
        return user.isValidBadgeSet();
    }

    // Convert User entity to UserDto
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setScore(user.getScore());
        dto.setBadges(user.getBadges().stream().map(BadgeEnum::getBadgeName).collect(Collectors.toSet()));
        return dto;
    }
}
