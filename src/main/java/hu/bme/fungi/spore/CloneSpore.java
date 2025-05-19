package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

/**
 * Represents a Clone Spore in the game, a type of spore that can clone an insect when its effect is applied.
 * This class extends the base Spore class and overrides methods to provide specific behavior for cloning.
 */
public class CloneSpore extends Spore {

    /**
     * Constructs a new CloneSpore instance, initializing its texture with a specific image.
     */
    public CloneSpore() {
        super();
        textureProvider.setImage("/images/fungi_images/clone_spore.png");
    }

    /**
     * Creates and returns a new instance of CloneSpore, effectively cloning this spore.
     *
     * @return a new CloneSpore instance
     */
    public Spore clone() {
        return new CloneSpore();
    }

    /**
     * Applies the cloning effect to the specified insect, triggering the entomologist to clone the insect.
     *
     * @param insect the Insect object to which the cloning effect is applied
     */
    @Override
    public void applyEffect(Insect insect) {
        insect.getEntomologist().clone(insect);
    }
}
