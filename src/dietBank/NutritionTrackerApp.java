package dietBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

public class NutritionTrackerApp {
    private JFrame frame;
    private List<Meal> mealList;
    private NutritionData nutritionData;
    private DefaultListModel<String> mealDisplayList;

    public NutritionTrackerApp() {
        // Initialize data and GUI components
        frame = new JFrame("Nutrition Tracker");
        mealList = new ArrayList<>();
        nutritionData = new NutritionData();
        mealDisplayList = new DefaultListModel<>();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create GUI elements
        createGUI();

        // Load data from file
        loadMealsFromFile();

        // Make the frame visible
        frame.setVisible(true);
    }

    private void createGUI() {
        // Panel for adding meals
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        JTextField nameField = new JTextField();
        JTextField caloriesField = new JTextField();
        JTextField proteinField = new JTextField();
        JTextField carbsField = new JTextField();
        JTextField fatsField = new JTextField();
        JButton addMealButton = new JButton("Add Meal");

        inputPanel.add(new JLabel("Meal Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Calories:"));
        inputPanel.add(caloriesField);
        inputPanel.add(new JLabel("Protein:"));
        inputPanel.add(proteinField);
        inputPanel.add(new JLabel("Carbs:"));
        inputPanel.add(carbsField);
        inputPanel.add(new JLabel("Fats:"));
        inputPanel.add(fatsField);
        inputPanel.add(addMealButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // List to display meals
        JList<String> mealListView = new JList<>(mealDisplayList);
        JScrollPane scrollPane = new JScrollPane(mealListView);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel for totals
        JPanel totalPanel = new JPanel(new GridLayout(4, 1));
        JLabel totalCaloriesLabel = new JLabel("Total Calories: 0");
        JLabel totalProteinLabel = new JLabel("Total Protein: 0.0");
        JLabel totalCarbsLabel = new JLabel("Total Carbs: 0.0");
        JLabel totalFatsLabel = new JLabel("Total Fats: 0.0");

        totalPanel.add(totalCaloriesLabel);
        totalPanel.add(totalProteinLabel);
        totalPanel.add(totalCarbsLabel);
        totalPanel.add(totalFatsLabel);

        frame.add(totalPanel, BorderLayout.SOUTH);

        // Add functionality to the "Add Meal" button
        addMealButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int calories = Integer.parseInt(caloriesField.getText());
                double protein = Double.parseDouble(proteinField.getText());
                double carbs = Double.parseDouble(carbsField.getText());
                double fats = Double.parseDouble(fatsField.getText());
                LocalDate date = LocalDate.now();

                Meal meal = new Meal(name, calories, protein, carbs, fats, date);
                mealList.add(meal);
                nutritionData.addMeal(meal);

                // Update UI
                mealDisplayList.addElement(name + " (" + calories + " cal)");
                totalCaloriesLabel.setText("Total Calories: " + nutritionData.getTotalCalories());
                totalProteinLabel.setText("Total Protein: " + nutritionData.getTotalProtein());
                totalCarbsLabel.setText("Total Carbs: " + nutritionData.getTotalCarbs());
                totalFatsLabel.setText("Total Fats: " + nutritionData.getTotalFats());

                // Clear input fields
                nameField.setText("");
                caloriesField.setText("");
                proteinField.setText("");
                carbsField.setText("");
                fatsField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for calories, protein, carbs, and fats.");
            }
        });
    }

    public void loadMealsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("meals.dat"))) {
            mealList = (List<Meal>) ois.readObject();
            for (Meal meal : mealList) {
                nutritionData.addMeal(meal);
                mealDisplayList.addElement(meal.getName() + " (" + meal.getCalories() + " cal)");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NutritionTrackerApp::new);
    }
}