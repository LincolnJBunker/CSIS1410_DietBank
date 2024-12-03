package dietBank;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * The Meal class represents the nutritional information of a given meal.
 * Each meal has a name, nutritional values (calories, protein, carbs, fats),
 * and the date it was consumed.
 */
public class Meal implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private final String name;
    private final int calories;
    private final double protein;
    private final double carbs;
    private final double fats;
    private final LocalDate date;

    /**
     * Constructor for the Meal class.
     * Initializes all the fields.
     * 
     * @param name The name of the meal.
     * @param calories The number of calories in the meal.
     * @param protein The amount of protein in the meal (grams).
     * @param carbs The amount of carbohydrates in the meal (grams).
     * @param fats The amount of fat in the meal (grams).
     * @param date The date the meal was consumed.
     * 
     * @author Lincoln
     */
    public Meal(String name, int calories, double protein, double carbs, double fats, LocalDate date) {
        if (calories < 0 || protein < 0 || carbs < 0 || fats < 0) {
            throw new IllegalArgumentException("Nutritional values cannot be negative.");
        }
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.date = date;
    }

    /**
     * Getter method for the meal name.
     * 
     * @return The name of the meal.
     * @author Lincoln
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the calories in the meal.
     * 
     * @return The number of calories in the meal.
     * @author Lincoln
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Getter method for the protein content of the meal.
     * 
     * @return The amount of protein in the meal (grams).
     * @author Lincoln
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Getter method for the carbohydrate content of the meal.
     * 
     * @return The amount of carbohydrates in the meal (grams).
     * @author Lincoln
     */
    public double getCarbs() {
        return carbs;
    }

    /**
     * Getter method for the fat content of the meal.
     * 
     * @return The amount of fat in the meal (grams).
     * @author Lincoln
     */
    public double getFats() {
        return fats;
    }

    /**
     * Getter method for the date the meal was consumed.
     * 
     * @return The date of the meal.
     * @author Lincoln
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string representation of the meal, including its nutritional values.
     * 
     * @return A formatted string representing the meal.
     * @author Ryan
     */
    @Override
    public String toString() {
        return String.format(
            "Meal[name='%s', calories=%d, protein=%.2f, carbs=%.2f, fats=%.2f, date=%s]",
            name, calories, protein, carbs, fats, date
        );
    }
}
