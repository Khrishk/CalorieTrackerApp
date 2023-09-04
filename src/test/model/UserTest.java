package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private User testUserMale;
    private User testUserFemale;
    private User testUserFemale2;

    @BeforeEach
    void runBefore(){ testUserMale = new User(175,98.5,34,1, 1);
        testUserFemale = new User(152.4,53.5,24,2, 2);
        testUserFemale2 = new User(152.4,53.5,24,2, 3);
    }

    @Test
    void testConstructor() {
        assertEquals(175,testUserMale.getHeight());
        assertEquals(98.5,testUserMale.getWeight());
        assertEquals(34,testUserMale.getAge());
        assertEquals(1, testUserMale.getSex());
    }

    @Test
    void testBmrCalculateMale() {
        assertEquals(1939.2849999999999, testUserMale.getBmr());
    }

    @Test
    void testBmrCalculateFemale() {
        assertEquals(1224.6850000000002, testUserFemale.getBmr());
    }

    @Test
    void testCalorieGoal() {
        assertEquals(2539.285, testUserMale.getCalorieGoal());
        assertEquals(2224.6850000000004, testUserFemale.getCalorieGoal());
        assertEquals(1524.6850000000002, testUserFemale2.getCalorieGoal());

    }


}