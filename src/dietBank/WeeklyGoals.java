package dietBank;

public class WeeklyGoals {
    private double goalCalories = 0;
    private double goalProtein = 0;
    private double goalCarbs = 0;
    private double goalFats = 0;

    /**
     * Constructor for class WeeklyGoals. Initializes the fields
     * 
     * @param goalCalories
     * @param goalProtein
     * @param goalCarbs
     * @param goalFats
     * @author Lincoln
     */
    public WeeklyGoals(double goalCalories, double goalProtein, double goalCarbs, double goalFats) {
        this.goalCalories = goalCalories;
        this.goalProtein = goalProtein;
        this.goalCarbs = goalCarbs;
        this.goalFats = goalFats;
    }

    /**
     * getter method for goal calories
     * @return goalCalories
     * @author Lincoln
     */
    public double getGoalCalories() {
        return goalCalories;
    }
    
    /**
     * setter method for goal calories
     * @param goalCalories
     * @author Lincoln
     */
    public void setGoalCalories(double goalCalories) {
        this.goalCalories = goalCalories;
    }
    
    /**
     * getter method for goal protein
     * @return goalProtein
     * @author Lincoln
     */
    public double getGoalProtein() {
        return goalProtein;
    }
    
    /**
     * setter method for goal protein
     * @param goalProtein
     * @author Lincoln
     */
    public void setGoalProtein(double goalProtein) {
        this.goalProtein = goalProtein;
    }
    
    /**
     * getter method for goal carbs
     * @return goalCarbs
     * @author Lincoln
     */
    public double getGoalCarbs() {
        return goalCarbs;
    }
    
    /**
     * setter method for goal carbs
     * @param goalCarbs
     * @author Lincoln
     */
    public void setGoalCarbs(double goalCarbs) {
        this.goalCarbs = goalCarbs;
    }
    
    /**
     * getter method for goal fats
     * @return goalFats
     * @author Lincoln
     */
    public double getGoalFats() {
        return goalFats;
    }
    
    
    /**
     * setter method for goal fats
     * @param goalFats
     * @author Lincoln
     */
    public void setGoalFats(double goalFats) {
        this.goalFats = goalFats;
    }
}

