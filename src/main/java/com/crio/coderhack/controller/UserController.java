package com.crio.coderhack.controller;

import com.crio.coderhack.dto.ApiResponseDto;
import com.crio.coderhack.dto.ScoreDto;
import com.crio.coderhack.dto.UserDto;
import com.crio.coderhack.exception.UserNotFoundException;
import com.crio.coderhack.exception.InvalidScoreException;
import com.crio.coderhack.services.IUserService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coderhack/api/v1")
@Tag(name = "User Management", description = "Operations for managing users in the CoderHack application.")
public class UserController {

    private static final String USER_API_ENDPOINT = "/users";

    @Autowired
    private IUserService userService;

    // Get all Users
    @GetMapping(USER_API_ENDPOINT)
    @Operation(
        summary = "Retrieve all users",
        description = "Fetch a list of all users registered in the CoderHack system."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users.")
    @ApiResponse(responseCode = "500", description = "An internal server error occurred while fetching users.")
    public ResponseEntity<ApiResponseDto<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ApiResponseDto<List<UserDto>> response = new ApiResponseDto<>(
            "success",
            "Users retrieved successfully",
            users
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping(USER_API_ENDPOINT + "/{userId}")
    @Operation(
        summary = "Retrieve user by ID",
        description = "Fetch details of a user by their unique ID."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the user details.")
    @ApiResponse(responseCode = "404", description = "User with the specified ID was not found.")
    @ApiResponse(responseCode = "500", description = "An internal server error occurred while fetching the user.")
    public ResponseEntity<ApiResponseDto<UserDto>> getUserById(@PathVariable String userId) {
        Optional<UserDto> user = userService.getUserById(userId);
        if (user.isPresent()) {
            ApiResponseDto<UserDto> response = new ApiResponseDto<>(
                "success",
                "User retrieved successfully",
                user.get()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseDto<UserDto> response = new ApiResponseDto<>(
                "error",
                "User not found",
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Create a new user
    @PostMapping(USER_API_ENDPOINT)
    @Operation(
        summary = "Create a new user",
        description = "Register a new user in the CoderHack system with the provided details."
    )
    @ApiResponse(responseCode = "201", description = "User created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid user data provided.")
    @ApiResponse(responseCode = "500", description = "An internal server error occurred while creating the user.")
    public ResponseEntity<ApiResponseDto<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        if (userDto.getUserId() == null || userDto.getUserName() == null) {
            ApiResponseDto<UserDto> response = new ApiResponseDto<>(
                "error",
                "Invalid user data",
                null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        UserDto createdUser = userService.addUser(userDto);
        ApiResponseDto<UserDto> response = new ApiResponseDto<>(
            "success",
            "User created successfully",
            createdUser
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update user's score
    @PutMapping(USER_API_ENDPOINT + "/{userId}")
    @Operation(
        summary = "Update user score",
        description = "Modify the score for a specific user based on their ID."
    )
    @ApiResponse(responseCode = "200", description = "User score updated successfully.")
    @ApiResponse(responseCode = "404", description = "User with the specified ID was not found.")
    @ApiResponse(responseCode = "400", description = "Invalid score value provided.")
    @ApiResponse(responseCode = "500", description = "An internal server error occurred while updating the score.")
    public ResponseEntity<ApiResponseDto<UserDto>> updateUserScore(@PathVariable String userId, @Valid @RequestBody ScoreDto scoreDto) {
        try {
            UserDto updatedUser = userService.updateUserScore(userId, scoreDto.getScore());
            ApiResponseDto<UserDto> response = new ApiResponseDto<>(
                "success",
                "User score updated successfully",
                updatedUser
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            ApiResponseDto<UserDto> response = new ApiResponseDto<>(
                "error",
                "User not found",
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (InvalidScoreException e) {
            ApiResponseDto<UserDto> response = new ApiResponseDto<>(
                "error",
                "Invalid score",
                null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a user
    @DeleteMapping(USER_API_ENDPOINT + "/{userId}")
    @Operation(
        summary = "Delete a user",
        description = "Remove a user from the CoderHack system using their ID."
    )
    @ApiResponse(responseCode = "204", description = "User deleted successfully.")
    @ApiResponse(responseCode = "404", description = "User with the specified ID was not found.")
    @ApiResponse(responseCode = "500", description = "An internal server error occurred while deleting the user.")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            ApiResponseDto<Void> response = new ApiResponseDto<>(
                "success",
                "User deleted successfully",
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            ApiResponseDto<Void> response = new ApiResponseDto<>(
                "error",
                "User not found",
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
