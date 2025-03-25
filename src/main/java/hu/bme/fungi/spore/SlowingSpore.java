package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Class representing a SlowingSpore.
 * A SlowingSpore if eaten by an {@link Insect} will decrease its movement speed for a time.
 */
public class SlowingSpore extends Spore {

    /**
     * Initializes a SlowingSpore.
     */
    public SlowingSpore() {
        super();
    }

    /**
     * Initializes a SlowingSpore with the given arguments
     * @param nutrition Nutritional value of the spore
     * @param effectRate Effect Rate of the spore
     */
    public SlowingSpore(float nutrition, float effectRate){
        super(nutrition, effectRate);
    }

    /**
     * Applies the effect of the SlowingSpore on an {@link Insect}.
     * @param insect the insect to apply the effect on
     */
    @Override
    public void applyEffect(Insect insect) {
        // TODO implement the function
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }
    
}
