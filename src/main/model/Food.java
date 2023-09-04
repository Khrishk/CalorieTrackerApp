package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents food with a food name and the number of calories it has
// Citation : Uses code from sample file "JsonSerializationDemo"
public class Food implements Writable {

    private String name;
    private int calories;

    // Effects: constructs a food with a food name and number of calories
    public Food(String name, int calories) {
        this.calories = calories;
        this.name = name;
    }

    // EFFECT: returns Json of Food
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calories", calories);
        return json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Food food = (Food) o;

        if (calories != food.calories) {
            return false;
        }
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + calories;
        return result;
    }
}
