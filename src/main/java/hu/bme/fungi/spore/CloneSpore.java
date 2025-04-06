package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

public class CloneSpore extends Spore {

    public CloneSpore() {
        super();
    }

    @Override
    public void applyEffect(Insect insect) {
        insect.getEntomologist().clone(insect);
    }
    
}
