package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    private Food testFood;
    private Food testFood2;
    private Food testFood3;

    @BeforeEach

    void runBefore() {
        testFood = new Food("apple", 60 );
        testFood2 = new Food("apple", 60 );
        testFood3 = new Food("banana", 60 );
    }

    @Test
    void testConstructor() {
        assertEquals("apple", testFood.getName());
        assertEquals(60, testFood.getCalories());

    }

    @Test
    void testSetCalories() {
        testFood.setCalories(120);
        assertEquals(120, testFood.getCalories());
    }

    @Test
    void testSetName() {
        testFood.setName("banana");
        assertEquals("banana", testFood.getName());
    }

    @Test
    void testEquals() {
        assertTrue(testFood.equals(testFood));
        assertTrue(testFood.equals(testFood2));
        assertTrue(testFood2.equals(testFood));
        assertFalse(testFood.equals(testFood3));
        assertFalse(testFood3.equals(testFood2));
    }




}
