package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Class representing a DefensiveSpore.
 * A DefensiveSpore if eaten by an {@link Insect} will not let it cut a {@link Hyphae} for a given time.
 */
public class DefensiveSpore extends Spore{

    public DefensiveSpore() {
        super();
    }

    @Override
    public void applyEffect(Insect insect) {
        // TODO add javadoc
        insect.setCanCutHyphae(false);
    }
}
