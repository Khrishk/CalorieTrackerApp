package model;


// Represents a User to use the Calorie Tracker
// Citation : Uses code from sample file "JsonSerializationDemo"
public class User {

    private double height; // User's height in cm

    private double weight; // User's weight in kg

    private int age; // User's weight in kg
    private int sex; // User's sex (1 == male 2 == female)

    private int goal; // User's goal (1 == Maintain, 2 == Gain weight, 3 == Lose weight)


    // Requires: sex to be either 1 or 2, goal to be either 1,2, or 3
    // Effects: constructs a User with a given height, weight, age, sex, and fitness goal
    public User(double height, double weight, int age, int sex, int goal) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.goal = goal;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }


    // Requires: sex to be either 1 or 2
    // Modifies: this
    // Effects: returns the basal metabolic rate of the user using given inputs and the Mifflin-St Jeor equations
    public double bmrCalculate() {
        double bmr = 0;

        if (sex == 1) {
            //Mifflin-St Jeor equation male
            bmr = (height * 6.25) + (weight * 9.99) - (age * 4.22) + 5;
        } else {
            //Mifflin-St Jeor equation female
            bmr = (height * 6.25) + (weight * 9.99) - (age * 4.22) - 161;
        }
        EventLog.getInstance().logEvent(new Event("BMR Calculated."));
        return bmr;
    }


    // Requires: goal to be either 1,2, or 3
    // Modifies: this
    // Effects: returns the number of calories the user should be eating based on their bmr and fitness goal
    public double calorieGoal() {
        double calorieGoal = 0;
        if (goal == 1) {
            calorieGoal = getBmr() + 600;
        } else if (goal == 2) {
            calorieGoal = getBmr() + 1000;
        } else {
            calorieGoal = getBmr() + 300;
        }
        EventLog.getInstance().logEvent(new Event("Calculated Calorie Goal"));

        return calorieGoal;
    }

    public double getBmr() {
        return bmrCalculate();
    }

    public double getCalorieGoal() {
        return calorieGoal();
    }

}
