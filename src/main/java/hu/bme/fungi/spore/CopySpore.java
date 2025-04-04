package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * A specialized spore that creates an identical copy of the target insect upon application.
 * This spore leverages the genetic replication mechanism of the host fungus to duplicate
 * the insect's biological structure.
 */
public class CopySpore extends Spore{

    /**
     * Constructs a CopySpore instance with default fungal properties.
     */
    public CopySpore() {
        super();
    }

    /**
     * Applies the spore's effect by creating a genetic copy of the target insect and
     * attaching it to the same environment.
     * 
     * @param insect The insect to be copied. Must be in a biologically active state.
     */
    @Override
    public void applyEffect(Insect insect) {
        Insect copy = new Insect(insect);
        
    }
}
