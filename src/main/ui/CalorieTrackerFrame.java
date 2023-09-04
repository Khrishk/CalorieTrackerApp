package ui;

import model.Event;
import model.EventLog;
import model.Food;
import model.FoodList;
import model.exception.LogException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class CalorieTrackerFrame extends JDialog implements LogPrinter {

    private static final String JSON_STORE = "./data/foodlist.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField tfName;
    private JLabel nameLabel;
    private JTextField totalCalories;
    private JPanel trackerPanel;
    private JButton foodButton;
    private JTextField tfCalories;
    private JButton homeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton removeFoodButton;
    private FoodList totalCals;

    //  EFFECTS: Constructs a JFrame for the Calorie Tracker panel
    public CalorieTrackerFrame() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        startPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {

            // EFFECTS: Prints the EventLog and quits the system
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    printLog(EventLog.getInstance());
                } catch (LogException e) {
                    throw new RuntimeException(e);
                }
                //THEN you can exit the program
                System.exit(0);
            }
        });

        foodButton.addActionListener(e -> addFood());
        homeButton.addActionListener(e -> {
            setVisible(false);
            new MainFrame();
        });

        saveButton.addActionListener(e -> save());
        loadButton.addActionListener(e -> load());
        removeFoodButton.addActionListener(e -> removeFood());

        setVisible(true);
    }

    // EFFECTS: Instantiates the panel
    private void startPanel() {
        setContentPane(trackerPanel);
        setTitle("Tracker");
        setSize(550, 450);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        totalCals = new FoodList();
        displayCals();
    }


    // EFFECTS: Removes food from foodList
    // MODIFIES: this, foodList
    private void removeFood() {

        String name = tfName.getText();
        int calories = Integer.parseInt(tfCalories.getText());
        Food food = new Food(name, calories);
        totalCals.removeFood(food);
        tfName.setText("");
        tfCalories.setText("");
        displayCals();
    }

    // EFFECTS: loads foodList.json from /data directory
    // MODIFIES: this, foodList
    private void load() {
        try {
            totalCals = jsonReader.read();
            System.out.println("Loaded your food list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        displayCals();
    }

    // EFFECTS: saves foodList to foodList.json in /data directory
    // MODIFIES: this, foodList
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(totalCals);
            jsonWriter.close();
            System.out.println("Saved your food list to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: Adds food from foodList
    // MODIFIES: this, foodList
    private void addFood() {

        String name = tfName.getText();
        int calories = Integer.parseInt(tfCalories.getText());
        Food food = new Food(name, calories);
        totalCals.addFood(food);
        tfName.setText("");
        tfCalories.setText("");
        displayCals();
    }

    // EFFECTS: displays totalCalories
    // MODIFIES: this
    private void displayCals() {

        int total = totalCals.getTotalCalories();
        totalCalories.setText(String.valueOf(total));
    }

    // EFFECTS: Prints the EventLog
    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.getDescription());
        }
    }

}
