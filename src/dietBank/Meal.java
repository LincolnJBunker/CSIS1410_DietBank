package dietBank;

import java.time.LocalDate;

/**
 * class Meal and its fields, constructors, and getter methods
 * defines the nutritional information about a given meal
 */
public class Meal {
	public final String name;
	public final int calories;
	public final double protein;
	public final double carbs;
	public final double fats;
	public final LocalDate date;
	
	/**
	 * Constructor for class Meal
	 * Initializes all of the fileds
	 * @param name the name of the meal
	 * @param calories how many calories the meal has
	 * @param protein how much protein the meal has
	 * @param carbs how many carbs the meal has
	 * @param fats how much fat there is in a meal
	 * @param date the date the meal was taken
	 */
	public Meal(String name, int calories, double protein, double carbs, double fats, LocalDate date) {
		this.name = name;
		this.calories = calories;
		this.protein = protein;
		this.carbs = carbs;
		this.fats = fats;
		this.date = date;
	}
	
	/**
	 * Getter method for name
	 * @return the name of the meal
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for calories
	 * @return the number of calories in a meal
	 */
	public int getCalories() {
		return calories;
	}
	
	/**
	 * Getter method for protein
	 * @return the amount of protein in the meal
	 */
	public double getProtein() {
		return protein;
	}
	
	/**
	 * Getter method for carbs
	 * @return the amount of carbs in a meal
	 */
	public double getCarbs() {
		return carbs;
	}
	
	/**
	 * Getter method for fats
	 * @return the amount of fat in a meal
	 */
	public double getFats() {
		return fats;
	}
	
	/**
	 * Getter method for date
	 * @return the date the meal was eaten
	 */
	public LocalDate getDate() {
		return date;
	}
}
