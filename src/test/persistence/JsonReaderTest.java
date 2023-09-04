package persistence;

import model.FoodList;
import model.Food;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodList wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFoodList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFoodList.json");
        try {
            FoodList wr = reader.read();
            assertEquals(0, wr.getTotalCalories());
            assertTrue(wr.getList().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFoodList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFoodList.json");
        try {
            FoodList wr = reader.read();
            assertEquals(1295, wr.getTotalCalories());
            List<Food> foodList = wr.getList();
            assertEquals(2, foodList.size());
            checkFood("apple", 95, foodList.get(0));
            checkFood("cheese", 1200, foodList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}