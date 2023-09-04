package persistence;

import model.Food;
import model.FoodList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            FoodList wr = new FoodList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFoodList() {
        try {
            FoodList wr = new FoodList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals(0, wr.getTotalCalories());
            assertTrue(wr.getList().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFoodList() {
        try {
            FoodList wr = new FoodList();
            wr.addFood(new Food("apple", 95));
            wr.addFood(new Food("burger", 550));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            List<Food> foodList = wr.getList();
            assertEquals(2, foodList.size());
            checkFood("apple", 95, foodList.get(0));
            checkFood("burger", 550, foodList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}