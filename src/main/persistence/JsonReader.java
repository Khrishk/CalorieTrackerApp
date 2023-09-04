package persistence;

import model.Food;
import model.FoodList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads foodList from JSON data stored in file
// Citation : Uses code from sample file "JsonSerializationDemo"
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads foodlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FoodList from JSON object and returns it
    private FoodList parseFoodList(JSONObject jsonObject) {
       // String totalCalories = jsonObject.getString("totalCalories");
        FoodList wr = new FoodList();
        addFoodLists(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses foodList from JSON object and adds them to foodList
    private void addFoodLists(FoodList wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodList");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(wr, nextFood);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses Food from JSON object and adds it to foodList
    private void addFood(FoodList wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int calories = jsonObject.getInt("calories");
        Food food = new Food(name, calories);
        wr.addFood(food);
    }
}
