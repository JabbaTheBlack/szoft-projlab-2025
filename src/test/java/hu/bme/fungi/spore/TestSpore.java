package hu.bme.fungi.spore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSpore {
    private StunSpore stunspore;
    @BeforeEach
    void setUp() {
        stunspore = new StunSpore();
        
    }

    @Test
    void testInitialization() {
 
        assertEquals(stunspore.getNutrition(), 1, "Nutrition Value should be 1");
        assertEquals(stunspore.getEffectRate(), 1, "Effect Rate value should be 1");

        DefensiveSpore defensiveSpore = new DefensiveSpore(5, 7);

        assertEquals(defensiveSpore.getNutrition(), 5, "Nutritional value should be 5");
        assertEquals(defensiveSpore.getEffectRate(), 7, "Effect Rate should be 7");

        
        SlowingSpore slowingSpore = new SlowingSpore(5, 7);

        assertEquals(slowingSpore.getNutrition(), 5, "Nutritional value should be 5");
        assertEquals(slowingSpore.getEffectRate(), 7, "Effect Rate should be 7");
    }
}
