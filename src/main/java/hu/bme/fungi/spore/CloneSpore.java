package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

public class CloneSpore extends Spore {

    public CloneSpore() {
        super();
        textureProvider.setImage("/images/fungi_images/clone_spore.png");
    }

    public Spore clone() {
        return new CloneSpore();
    }

    @Override
    public void applyEffect(Insect insect) {
        insect.getEntomologist().clone(insect);
    }

}
