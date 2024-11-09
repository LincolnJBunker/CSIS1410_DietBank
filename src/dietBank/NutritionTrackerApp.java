package dietBank;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

public class NutritionTrackerApp {
    private JFrame frame;
    private List<Meal> mealList;
    private NutritionData nutritionData;

    public NutritionTrackerApp() {
        // Initialize GUI components
        frame = new JFrame("Nutrition Tracker");
        mealList = new ArrayList<>();
        nutritionData = new NutritionData();
        
        // Basic setup for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Add GUI elements and initialize components here
        // (e.g., buttons, panels, text fields, etc.)

        // Set the frame to be visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Launch the application
        SwingUtilities.invokeLater(() -> new NutritionTrackerApp());
    }

    public void loadMealsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("meals.dat"))) {
            mealList = (List<Meal>) ois.readObject();
            for (Meal meal : mealList) {
                nutritionData.addMeal(meal);
            }
            System.out.println("Meals loaded successfully from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading meals from file: " + e.getMessage());
        }
    }

    public void saveMealsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("meals.dat"))) {
            oos.writeObject(mealList);
            System.out.println("Meals saved successfully to file.");
        } catch (IOException e) {
            System.out.println("Error saving meals to file: " + e.getMessage());
        }
    }
}