package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Class representing a SpeedBoostSpore.
 * A SpeedBoostSpore if eaten by an {@link Insect} will increase its movement speed for a time. 
 */
public class SpeedBoostSpore extends Spore {
    
    /**
     * Initializes a SpeedBoostSpore.
     */
    public SpeedBoostSpore() {
        super();
    }

    /**
     * Applies the effect of the SpeedBoostSpore on an {@link Insect}.
     * @param insect the insect to apply the effect on
     */
    @Override
    public void applyEffect(Insect insect) {
        // TODO implement the function
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }
}
