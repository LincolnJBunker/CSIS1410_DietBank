package dietBank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The User class contains fields, constructor, and methods that define a user.
 * A user has a username, email, password, and points for rewards tracking.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4842828555159137740L;
    private final String username;
    private final String email;
    private final String password;
    private int points; // Tracks points for rewards
    private WeeklyGoals weeklyGoals;

    /**
     * Constructor for the User class.
     * Initializes the username, email, password, and points.
     *
     * @param username The user's username.
     * @param email    The user's email.
     * @param password The user's password.
     * @author Lincoln
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.points = 0; // Initialize points to zero
    }

    /**
     * Getter method for username.
     *
     * @return The user's username.
     * @author Lincoln
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method for email.
     *
     * @return The user's email.
     * @author Lincoln
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter method for password.
     *
     * @return The user's password.
     * @author Lincoln
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter method for points.
     *
     * @return The user's current points.
     * @author Ryan
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the user's points to a specified value.
     *
     * @param points The new points value to set.
     * @throws IllegalArgumentException if the points are negative.
     * @author Ryan
     */
    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        this.points = points;
    }

    /**
     * Adds points to the user's total.
     *
     * @param points The number of points to add.
     * @author Ryan
     */
    public void addPoints(int points) {
        if (points > 0) {
            this.points += points;
        }
    }

    /**
     * Redeems points from the user's total.
     *
     * @param points The number of points to redeem.
     * @throws IllegalArgumentException if the user doesn't have enough points.
     * @author Ryan and Lincoln
     */
    public void redeemPoints(int points) {
        if (points > this.points) {
            throw new IllegalArgumentException("Not enough points to redeem this reward.");
        }
        this.points -= points;
    }

    /**
     * Returns a list of available rewards with their required points.
     *
     * @return A list of available rewards.
     * @author Ryan and Lincoln
     */
    public List<String> getAvailableRewards() {
        List<String> rewards = new ArrayList<>();
        if (points >= 100) {
            rewards.add("Gift Card (100 points)");
        }
        if (points >= 200) {
            rewards.add("Movie Tickets (200 points)");
        }
        if (points >= 500) {
            rewards.add("DietBank Merchandise (500 points)");
        }
        return rewards;
    }

    /**
     * Redeems a specific reward based on the option selected.
     *
     * @param rewardOption The selected reward option (1, 2, or 3).
     * @throws IllegalArgumentException if the reward option is invalid or if the user doesn't have enough points.
     * @return The name of the redeemed reward.
     * @author Ryan and Lincoln
     */
    public String redeemReward(int rewardOption) {
        switch (rewardOption) {
            case 1: // Gift Card
                redeemPoints(100);
                return "Gift Card";
            case 2: // Movie Tickets
                redeemPoints(200);
                return "Movie Tickets";
            case 3: // DietBank Merchandise
                redeemPoints(500);
                return "DietBank Merchandise";
            default:
                throw new IllegalArgumentException("Invalid reward option.");
        }
    }
    
    public WeeklyGoals getWeeklyGoals() {
        return weeklyGoals;
    }

    public void setWeeklyGoals(WeeklyGoals weeklyGoals) {
        this.weeklyGoals = weeklyGoals;
    }
}
