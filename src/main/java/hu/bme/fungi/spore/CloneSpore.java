package hu.bme.fungi.spore;

import hu.bme.fungi.Hyphae;
import hu.bme.insect.Insect;
/*
* Class representing a CloneSpore.
* A CloneSpore if eaten by an {@link Insect} will be spawn down a new {@link Insect} that will have the same attributes as the original one.
* The new {@link Insect} will be spawned at the same position as the original one.
* The owner of the new {@link Insect} will be the same as the original one.
*/

public class CloneSpore extends Spore{
    /**
     * Initializes a CloneSpore.
     */
    public CloneSpore() {
        super();
    }
    /**
     * Applies the effect of the CloneSpore on an {@link Insect}.
     * @param insect the insect to apply the effect on
     */
    
    @Override
    public void applyEffect(Insect insect) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

}
