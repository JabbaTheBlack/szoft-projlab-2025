package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Class representing a StunSpore.
 * A StunSpore if eaten by an {@link Insect} will stun it for a time.
 */
public class StunSpore extends Spore {
    
    /**
     * Initializes a StunSpore.
     */
    public StunSpore() {
        super();
    }

    /**
     * Initializes a StunSpore with the given arguments
     * @param nutrition Nutritional value of the spore
     * @param effectRate Effect Rate of the spore
     */
    public StunSpore(float nutrition, float effectRate){
        super(nutrition, effectRate);
    }

    /**
     * Applies the effect of the StunSpore on an {@link Insect}.
     * @param insect the insect to apply the effect on
     */
    @Override
    public void applyEffect(Insect insect) {
        // TODO implemen the function, add javadoc
        System.out.println("[StunSpore] setMovementSpeed(0) -> [" + insect + "]");
        insect.setMovementSpeed(0);
    }
}
