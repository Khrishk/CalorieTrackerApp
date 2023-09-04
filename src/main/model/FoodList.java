package model;

import model.exception.LogException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.LogPrinter;


import java.util.ArrayList;
import java.util.List;

// Represents a food list
// Citation : Uses code from sample file "JsonSerializationDemo"
public class FoodList implements Writable {

    private List<Food> foodList = new ArrayList<>();
    private int totalCalories;

    // EFFECTS: constructs a foodList and initializes totalCalories to 0
    public FoodList() {
        totalCalories = 0;
    }


    // MODIFIES: this
    // EFFECTS: Adds food to foodList and updates total calories consumed
    public void addFood(Food food) {
        foodList.add(food);
        totalCalories = totalCalories + food.getCalories();
        EventLog.getInstance().logEvent(new Event("Food Added."));

    }

    // MODIFIES: this
    // EFFECTS: Removes food from foodList and updates total calories consumed
    public void removeFood(Food food) {

        for (Food f : foodList) {
            if (food.equals(f)) {
                totalCalories = totalCalories - f.getCalories();
                foodList.remove(f);
                EventLog.getInstance().logEvent(new Event("Food removed."));
                return;
            }
        }

    }

    // EFFECT: returns Json of FoodList
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("totalCalories", totalCalories);
        json.put("foodList", foodListToJson());
        return json;
    }

    // EFFECTS: returns foods in this foodList as a JSON array
    private JSONArray foodListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foodList) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }


    public int getTotalCalories() {
        return totalCalories;
    }

    public List getList() {
        return foodList;
    }

}
