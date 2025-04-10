package hu.bme.fungi.mycelium;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.CloneSpore;
import hu.bme.tekton.Tekton;

public class CloneMycelium extends Mycelium<CloneSpore>{
    public CloneMycelium() {
        super();
    }

    public CloneMycelium(Tekton currentTekton) {
        super(currentTekton);
    }

    @Override
    public Mycelium<CloneSpore> cloneMycelium(Tekton targetTekton) {
        return new CloneMycelium(targetTekton);
    }

    @Override
    public Mycelium<CloneSpore> cloneMycelium() {
        return new CloneMycelium(); 
    }
}
