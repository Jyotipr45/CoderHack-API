package com.crio.coderhack.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.crio.coderhack.util.BadgeEnum;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    
    // Unique identifier for the user
    @Id
    private String userId;

    @NotNull(message = "Username cannot be null")
    private String userName;

    @Min(value = 0, message = "Score must be greater than or equal to 0")
    @Max(value = 100, message = "Score must be less than or equal to 100")
    private int score;

    // Set of badges earned by the user
    private Set<BadgeEnum> badges = new HashSet<>();

    // Updates badges based on the user's score
    public void updateBadges(int score) {
        if (score >= 60) {
            badges.add(BadgeEnum.CODE_MASTER);
        } else if (score >= 30) {
            badges.add(BadgeEnum.CODE_CHAMP);
        } else if (score >= 1) {
            badges.add(BadgeEnum.CODE_NINJA);
        }
    }

    // Checks if the badge set is valid (<= 3 badges)
    public boolean isValidBadgeSet() {
        return badges.size() <= 3;
    }
}
