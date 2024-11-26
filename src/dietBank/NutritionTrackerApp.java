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

    public void showLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(173, 216, 230));
        JLabel headerLabel = new JLabel("Nutirition Tracker");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(206, 229, 237));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        // Add components to form panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.setBackground(new Color(206, 229, 237));
        formPanel.add(buttonPanel, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            User authenticatedUser = authenticateUser(username, email, password);
            if (authenticatedUser != null) {
                currentUser = authenticatedUser;
                JOptionPane.showMessageDialog(loginFrame, "Login successful!");
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

        loginFrame.add(headerPanel, BorderLayout.NORTH);
        loginFrame.add(formPanel, BorderLayout.CENTER);

        loginFrame.setVisible(true);
    }

    private User authenticateUser(String username, String email, String password) {
        File userFile = new File("users.dat");
        if (userFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
                List<User> users = (List<User>) ois.readObject();
                for (User user : users) {
                    if (user.getUsername().equals(username) &&
                        user.getEmail().equals(email) &&
                        user.getPassword().equals(password)) {
                        return user; 
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
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
        // Main panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.setBackground(new Color(206, 229, 237));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create text fields for input
        JTextField nameField = new JTextField();
        JTextField caloriesField = new JTextField();
        JTextField proteinField = new JTextField();
        JTextField carbsField = new JTextField();
        JTextField fatsField = new JTextField();
        JButton addMealButton = new JButton("Add Meal");

        // Set fonts and colors for labels and buttons
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = Color.BLACK;
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonColor = new Color(0, 123, 255); // Blue color for buttons

        // Add components to the input panel
        inputPanel.add(createLabel("Meal Name:", labelFont, labelColor));
        inputPanel.add(nameField);
        inputPanel.add(createLabel("Calories:", labelFont, labelColor));
        inputPanel.add(caloriesField);
        inputPanel.add(createLabel("Protein:", labelFont, labelColor));
        inputPanel.add(proteinField);
        inputPanel.add(createLabel("Carbs:", labelFont, labelColor));
        inputPanel.add(carbsField);
        inputPanel.add(createLabel("Fats:", labelFont, labelColor));
        inputPanel.add(fatsField);
        inputPanel.add(createButton("Add Meal", addMealButton, buttonFont, buttonColor));

        frame.add(inputPanel, BorderLayout.NORTH);

        JList<String> mealListView = new JList<>(mealDisplayList);
        JScrollPane scrollPane = new JScrollPane(mealListView);
        scrollPane.setBackground(new Color(206, 229, 237));
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

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JButton createButton(String text, JButton button, Font font, Color color) {
        button.setText(text);
        button.setFont(font);
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
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