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
