package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Abstract class representing a spore in the game.
 * Spores have nutritional value and gives effects to {@link Insect} when eaten.
 */
public abstract class Spore {

    private float nutrition;

    /**
     * Constructs a spore with specified nutrition and effect rate.
     *
     * @param nutrition  The nutritional value of the spore.
     * @param effectRate The rate at which the spore applies its effect.
     */
    protected Spore(float nutrition, float effectRate) {
        this.nutrition = nutrition;
    }

    /**
     * Constructs a spore with default values.
     */
    protected Spore(){
        this(1, 1);
    }

    /**
     * Gets the nutritional value of the spore.
     * @return The nutritional value.
     */
    public float getNutrition() { return nutrition; }
    
    /**
     * Applies the spore's effect to a given insect.
     * The exact effect is dependent on the specific type of spore.
     * @param insect
     */
    public abstract void applyEffect(Insect insect);
}
