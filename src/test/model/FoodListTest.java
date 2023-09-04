package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodListTest {

    private FoodList testFoodList;
    private Food food1;
    private Food testFood2;
    private Food testFood3;




    @BeforeEach

    void runBefore() {
        testFoodList= new FoodList();
        food1 = new Food("Cheese Pizza", 1200);
        testFood2 = new Food("apple", 60 );
        testFood3 = new Food("banana", 60 );
    }

    @Test
    void testConstructor(){
        assertEquals(0, testFoodList.getTotalCalories());
    }

    @Test
    void testAddFood(){
        testFoodList.addFood(food1);
        assertEquals(1, testFoodList.getList().size());
        assertEquals(food1, testFoodList.getList().get(0));
        assertEquals(1200, testFoodList.getTotalCalories());
    }

    @Test
    void testRemoveFood(){
        testFoodList.addFood(food1);
        assertEquals(1, testFoodList.getList().size());
        assertEquals(food1, testFoodList.getList().get(0));
        assertEquals(1200, testFoodList.getTotalCalories());

        testFoodList.removeFood(food1);
        assertEquals(0, testFoodList.getList().size());
        assertEquals(0, testFoodList.getTotalCalories());

        testFoodList.addFood(testFood2);
        testFoodList.addFood(testFood3);
        testFoodList.removeFood(food1);
        assertEquals(2, testFoodList.getList().size());
        assertEquals(120, testFoodList.getTotalCalories());
        testFoodList.removeFood(testFood3);
        assertEquals(1, testFoodList.getList().size());
        assertEquals(60, testFoodList.getTotalCalories());

    }
}
