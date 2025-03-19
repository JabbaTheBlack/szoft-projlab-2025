package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Abstract class representing a spore in the game.
 * Spores have nutritional value and gives effects to {@link Insect} when eaten.
 */
public abstract class Spore {

    private float nutrition;
    private float effectRate;

    /**
     * Constructs a spore with specified nutrition and effect rate.
     *
     * @param nutrition  The nutritional value of the spore.
     * @param effectRate The rate at which the spore applies its effect.
     */
    public Spore(float nutrition, float effectRate) {
        this.nutrition = nutrition;
        this.effectRate = effectRate;
    }

    /**
     * Constructs a spore with default values.
     */
    public Spore(){
        this(1, 1);
    }

    /**
     * Gets the nutritional value of the spore.
     * @return The nutritional value.
     */
    public float getNutrition() { return nutrition; }

    /**
     * Gets the effect rate of the spore.
     * @return The effect rate.
     */
    public float getEffectRate() { return effectRate; }
    
    /**
     * Applies the spore's effect to a given insect.
     * The exact effect is dependent on the specific type of spore.
     * @param insect
     */
    public abstract void applyEffect(Insect insect);
}
