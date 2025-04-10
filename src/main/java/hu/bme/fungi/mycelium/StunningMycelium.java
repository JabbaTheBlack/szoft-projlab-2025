package hu.bme.fungi.mycelium;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.tekton.Tekton;

public class StunningMycelium extends Mycelium<StunSpore> {
    
    public StunningMycelium() {
        super();
    }

    public StunningMycelium(Tekton currentTekton) {
        super(currentTekton);
    }

    @Override
    public Mycelium<StunSpore> cloneMycelium(Tekton targetTekton) {
        return new StunningMycelium(targetTekton);
    }

    @Override
    public Mycelium<StunSpore> cloneMycelium() {
        return new StunningMycelium();
    }
}
