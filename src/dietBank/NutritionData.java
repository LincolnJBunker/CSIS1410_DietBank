package dietBank;


/**
 * NutritionData class is responsible for tracking the total nutritional values such as calories,
 * protein, carbohydrates, and fats for a collection of meals. It provides methods to add meals
 * and returns the total values for each category.
 */
class NutritionData {
    // Fields to store the total nutritional values
    private int totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFats;

    /**
     * Constructor for NutritionData.
     * Initializes the total nutritional values to zero.
     */
    public NutritionData() {
        totalCalories = 0;
        totalProtein = 0;
        totalCarbs = 0;
        totalFats = 0;
    }

    /**
     * Adds a Meal object to the current totals.
     * This method updates the totalCalories, totalProtein, totalCarbs, and totalFats fields
     * by adding the nutritional values from the provided meal.
     *
     * @param meal The Meal object to be added to the total nutritional values.
     */
    public void addMeal(Meal meal) {
        totalCalories += meal.getCalories();
        totalProtein += meal.getProtein();
        totalCarbs += meal.getCarbs();
        totalFats += meal.getFats();
    }

    /**
     * Calculates the total nutritional values.
     * In this implementation, the totals are updated when meals are added, so this method
     * does not perform any additional calculations.
     */
    public void calculateTotals() {
        // Calculations are done dynamically when meals are added, so nothing is needed here
    }

    /**
     * Getter method for the total calories from all added meals.
     *
     * @return The total calories as an integer.
     */
    public int getTotalCalories() {
        return totalCalories;
    }

    /**
     * Getter method for the total protein from all added meals.
     *
     * @return The total protein as a double.
     */
    public double getTotalProtein() {
        return totalProtein;
    }

    /**
     * Getter method for the total carbohydrates from all added meals.
     *
     * @return The total carbohydrates as a double.
     */
    public double getTotalCarbs() {
        return totalCarbs;
    }

    /**
     * Getter method for the total fats from all added meals.
     *
     * @return The total fats as a double.
     */
    public double getTotalFats() {
        return totalFats;
    }
}