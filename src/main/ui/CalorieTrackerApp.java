package ui;

import model.Food;
import model.FoodList;
import model.User;
import persistence.JsonWriter;
import persistence.JsonReader;


import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// Calorie Tracker App
public class CalorieTrackerApp {

    private static final String JSON_STORE = "./data/foodlist.json";
    private User user;
    private FoodList foodList;

    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Calorie Tracker application
    public CalorieTrackerApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        runCalorieTracker();
    }

    
    // MODIFIES: this
    // EFFECTS: processes user input
    public void runCalorieTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            doCalculateCalories();
        } else if (command.equals("t")) {
            doDailyTracker();
        } else if (command.equals("s")) {
            saveFoodList();
        } else if (command.equals("l")) {
            loadFoodList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        foodList = new FoodList();
       // user = new User(0,0,0,1,1);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: saves the workroom to file
    private void saveFoodList() {
        try {
            jsonWriter.open();
            jsonWriter.write(foodList);
            jsonWriter.close();
            System.out.println("Saved your food list to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFoodList() {
        try {
            foodList = jsonReader.read();
            System.out.println("Loaded your food list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Check how many calories you should be consuming daily");
        System.out.println("\tt -> Track how many calories you've eaten");
        System.out.println("\ts -> Save your current calories you've eaten today");
        System.out.println("\tl -> Load your current calories you've eaten today");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Calculates how many calories the user should be consuming daily
    public void doCalculateCalories() {
        System.out.println("Please enter your height:");
        double height = input.nextDouble();
        System.out.println("Please enter your weight:");
        double weight = input.nextDouble();
        System.out.println("Please enter a number to represent your sex (1 for male, 2 for female):");
        int sex = input.nextInt();
        System.out.println("Please enter your age:");
        int age = input.nextInt();
        System.out.println("Please enter a number to represent your fitness goal "
                + "(1 to Maintain, 2 to Gain weight, 3 to Lose weight):");
        int goal = input.nextInt();

        user = new User(height, weight, age, sex, goal);
        System.out.println("Your current Basal Metabolic Rate is: " + user.getBmr());
        System.out.println("The amount of calories you should be eating according to your goal is: "
                + user.getCalorieGoal());
    }

    // MODIFIES: this
    // EFFECTS: Calculates how many calories the user has consumed daily
    public void doDailyTracker() {

        boolean loopTemp = true;

        while (loopTemp == true) {
            System.out.println("Please write the name of food option from the menu:");
            System.out.println("Name           Calories per Unit");
            System.out.println("Apple                         95");
            System.out.println("Cheese Pizza                1200");
            System.out.println("Egg                           70");
            System.out.println("Scoop of Protein             120");
            String name = input.next();
            System.out.println("Please write the amount of calories as per the menu"
                    + "(or if you know the calories of the food your consuming you can enter it directly):");
            int calories = input.nextInt();

            Food tempFood = new Food(name, calories);
            foodList.addFood(tempFood);
            System.out.println("The total number of calories you've consumed today is " + foodList.getTotalCalories());
            System.out.println("Do you want to add more food? (y/n)");

            if (Objects.equals(input.next(), "n")) {
                loopTemp = false;
            }
        }
    }

}
