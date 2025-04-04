package hu.bme.fungi.spore;

import hu.bme.insect.Insect;

public class CopySpore extends Spore{
    public CopySpore() {
        super();
    }

    @Override
    public void applyEffect(Insect insect) {
        Insect copy = new Insect(insect);
        
    }
}
