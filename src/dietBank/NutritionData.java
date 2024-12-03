package dietBank;

import java.io.Serializable;
//import java.app

/**
 * NutritionData class is responsible for tracking the total nutritional values such as calories,
 * protein, carbohydrates, and fats for a collection of meals.
 * @author Ryan
 */
public class NutritionData implements Serializable {
    private static final long serialVersionUID = 1L;

    // Fields to store the total nutritional values
    private int totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFats;

    /**
     * Default constructor for NutritionData.
     * Initializes the total nutritional values to zero.
     * @author Ryan
     */
    public NutritionData() {
        totalCalories = 0;
        totalProtein = 0;
        totalCarbs = 0;
        totalFats = 0;
    }

    /**
     * Parameterized constructor for NutritionData.
     * Initializes the nutritional values with provided values.
     *
     * @param totalCalories Total calories.
     * @param totalProtein Total protein (grams).
     * @param totalCarbs Total carbohydrates (grams).
     * @param totalFats Total fats (grams).
     * @author Ryan
     */
    public NutritionData(int totalCalories, double totalProtein, double totalCarbs, double totalFats) {
        this.totalCalories = totalCalories;
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalFats = totalFats;
    }

    /**
     * Adds a Meal object to the current totals.
     *
     * @param meal The Meal object to add.
     * @author Ryan
     */
    public void addMeal(Meal meal) {
        totalCalories += meal.getCalories();
        totalProtein += meal.getProtein();
        totalCarbs += meal.getCarbs();
        totalFats += meal.getFats();
    }

    // Getters
    public int getTotalCalories() {
        return totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getTotalFats() {
        return totalFats;
    }

    /**
     * Resets the totals to zero.
     * @author Ryan
     */
    public void reset() {
        totalCalories = 0;
        totalProtein = 0;
        totalCarbs = 0;
        totalFats = 0;
    }
    
//    public String generateReport() {
//        StringBuilder report = new StringBuilder();
//        report.append("Week of ").append(LocalDate.now().minusDays(7)).append(":\n");
//        report.append(nutritionData.toString()).append("\n");
//        // Add more detailed daily breakdown if necessary
//        return report.toString();
//    }


    /**
     * Provides a string representation of the total nutritional data.
     *
     * @return A formatted string with the total values.
     * @author Ryan
     */
    @Override
    public String toString() {
        return String.format("Total Calories: %d, Protein: %.2f g, Carbs: %.2f g, Fats: %.2f g",
                totalCalories, totalProtein, totalCarbs, totalFats);
    }
}
