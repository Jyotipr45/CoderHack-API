package com.crio.coderhack.services;

import java.util.List;
import java.util.Optional;

import com.crio.coderhack.dto.UserDto;

public interface IUserService {

    // Retrieve all users
    List<UserDto> getAllUsers();

    // Find a user by their ID
    Optional<UserDto> getUserById(String userId);

    // Add a new user
    UserDto addUser(UserDto userDto);

    // Update the score for a user
    UserDto updateUserScore(String userId, int score);

    // Delete a user by their ID
    void deleteUser(String userId);
}
