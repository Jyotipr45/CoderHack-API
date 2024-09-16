package com.crio.coderhack.dto;

import java.util.Set;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    // Unique identifier for the user
    private String userId;

    // Name of the user
    @NotBlank(message = "Username cannot be blank")
    private String userName;

    // User's score
    @Min(value = 0, message = "Score must be greater than or equal to 0")
    @Max(value = 100, message = "Score must be less than or equal to 100")
    private int score;

    // Set of badges earned by the user
    private Set<String> badges;
}
