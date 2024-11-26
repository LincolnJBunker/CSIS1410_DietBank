package dietBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NutritionTrackerApp {
    private JFrame frame;
    private List<Meal> mealList;
    private NutritionData nutritionData;
    private DefaultListModel<String> mealDisplayList;
    private User currentUser;

    public NutritionTrackerApp() {
        // Initialize data
        mealList = new ArrayList<>();
        nutritionData = new NutritionData();
        mealDisplayList = new DefaultListModel<>();

        // Start with login screen
        showLoginScreen();
    }

    private void showLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new GridLayout(4, 2));

        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginFrame.add(new JLabel("Username:"));
        loginFrame.add(usernameField);
        loginFrame.add(new JLabel("Email:"));
        loginFrame.add(emailField);
        loginFrame.add(new JLabel("Password:"));
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);
        loginFrame.add(registerButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticateUser(username, email, password)) {
                currentUser = new User(username, email, password);
                loginFrame.dispose();
                showMainApp();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid credentials. Please try again.");
            }
        });

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (registerUser(username, email, password)) {
                JOptionPane.showMessageDialog(loginFrame, "Registration successful! Please log in.");
            } else {
                JOptionPane.showMessageDialog(loginFrame, "User already exists.");
            }
        });

        loginFrame.setVisible(true);
    }

    private boolean authenticateUser(String username, String email, String password) {
        File userFile = new File("users.dat");
        if (userFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
                List<User> users = (List<User>) ois.readObject();
                for (User user : users) {
                    if (user.getUsername().equals(username) &&
                        user.getEmail().equals(email) &&
                        user.getPassword().equals(password)) {
                        return true;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean registerUser(String username, String email, String password) {
        File userFile = new File("users.dat");
        List<User> users = new ArrayList<>();
        if (userFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
                users = (List<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // User already exists
            }
        }
        users.add(new User(username, email, password));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void showMainApp() {
        // Initialize main application GUI
        frame = new JFrame("Nutrition Tracker - " + currentUser.getUsername());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create GUI components
        createMainGUI();

        // Load user-specific meals
        loadMealsFromFile();

        frame.setVisible(true);
    }

    private void createMainGUI() {
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

        JList<String> mealListView = new JList<>(mealDisplayList);
        JScrollPane scrollPane = new JScrollPane(mealListView);
        frame.add(scrollPane, BorderLayout.CENTER);

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

                mealDisplayList.addElement(name + " (" + calories + " cal)");
                saveMealsToFile();

                nameField.setText("");
                caloriesField.setText("");
                proteinField.setText("");
                carbsField.setText("");
                fatsField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric values.");
            }
        });
    }

    private void loadMealsFromFile() {
        String fileName = currentUser.getUsername() + "_meals.dat";
        File mealFile = new File(fileName);
        if (mealFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(mealFile))) {
                mealList = (List<Meal>) ois.readObject();
                for (Meal meal : mealList) {
                    nutritionData.addMeal(meal);
                    mealDisplayList.addElement(meal.getName() + " (" + meal.getCalories() + " cal)");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMealsToFile() {
        String fileName = currentUser.getUsername() + "_meals.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(mealList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NutritionTrackerApp::new);
    }
}