package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Class representing a SpeedBoostSpore.
 * A SpeedBoostSpore if eaten by an {@link Insect} will increase its movement
 * speed for a time.
 */
public class SpeedBoostSpore extends Spore {

    /**
     * Initializes a SpeedBoostSpore.
     */
    public SpeedBoostSpore() {
        super();
        textureProvider.setImage("/images/fungi_images/speed_spore.png");
    }

    public Spore clone() {
        return new SpeedBoostSpore();
    }

    /**
     * Initializes a SpeedBoostSpore with the given arguments
     * 
     * @param nutrition  Nutritional value of the spore
     * @param effectRate Effect Rate of the spore
     */
    public SpeedBoostSpore(float nutrition, float effectRate) {
        super(nutrition, effectRate);
    }

    /**
     * Applies the effect of the SpeedBoostSpore on an {@link Insect}.
     * 
     * @param insect the insect to apply the effect on
     */
    @Override
    public void applyEffect(Insect insect) {
        // TODO implement the function
        // throw new UnsupportedOperationException("Unimplemented method
        // 'applyEffect'");
        insect.setMovementSpeed(insect.getMovementSpeed() * 2); // Increase speed by 100%
    }
}
