package dietBank;

import java.util.ArrayList;
import java.util.List;

/**
 * WeeklyNutritionReport class provides a breakdown of macronutrients over a week.
 * It aggregates daily nutritional data and calculates weekly totals and averages.
 */
public class WeeklyNutritionReport {
    private final List<Meal> weeklyMeals; // List to store meals for the week

    /**
     * Constructor for WeeklyNutritionReport.
     * Initializes an empty list to store weekly meals.
     * @author Ryan
     */
    public WeeklyNutritionReport() {
        weeklyMeals = new ArrayList<>();
    }

    /**
     * Adds a meal to the weekly report.
     *
     * @param meal The Meal object representing a meal for the day.
     * @author Ryan
     */
    public void addMeal(Meal meal) {
        weeklyMeals.add(meal);
    }

    /**
     * Calculates the total nutritional values for the week.
     *
     * @return A string containing the weekly total values.
     * @author Ryan
     */
    public String getWeeklyTotalAsString() {
        int totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFats = 0;

        // Aggregate nutrition from all meals in the week
        for (Meal meal : weeklyMeals) {
            totalCalories += meal.getCalories();
            totalProtein += meal.getProtein();
            totalCarbs += meal.getCarbs();
            totalFats += meal.getFats();
        }

        // Return formatted string with weekly totals
        return String.format(
            "Total Calories: %d\nTotal Protein: %.2f g\nTotal Carbs: %.2f g\nTotal Fats: %.2f g",
            totalCalories, totalProtein, totalCarbs, totalFats
        );
    }

    /**
     * Calculates the average nutritional values per meal for the week.
     *
     * @return A string containing the weekly average values.
     * @author Ryan
     */
    public String getWeeklyAverageAsString() {
        if (weeklyMeals.isEmpty()) {
            return "No meals added this week.";
        }

        // Calculate averages for each nutrient
        double avgCalories = weeklyMeals.stream().mapToInt(Meal::getCalories).average().orElse(0);
        double avgProtein = weeklyMeals.stream().mapToDouble(Meal::getProtein).average().orElse(0);
        double avgCarbs = weeklyMeals.stream().mapToDouble(Meal::getCarbs).average().orElse(0);
        double avgFats = weeklyMeals.stream().mapToDouble(Meal::getFats).average().orElse(0);

        // Return formatted string with weekly averages
        return String.format(
            "Avg Calories: %.2f\nAvg Protein: %.2f g\nAvg Carbs: %.2f g\nAvg Fats: %.2f g",
            avgCalories, avgProtein, avgCarbs, avgFats
        );
    }
}
