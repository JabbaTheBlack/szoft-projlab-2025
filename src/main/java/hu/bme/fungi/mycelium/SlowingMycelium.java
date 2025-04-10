package hu.bme.fungi.mycelium;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.SlowingSpore;
import hu.bme.tekton.Tekton;

public class SlowingMycelium extends Mycelium<SlowingSpore>{
    public SlowingMycelium() {
        super();
    }

    public SlowingMycelium(Tekton currentTekton) {
        super(currentTekton);
    }

    @Override
    public Mycelium<SlowingSpore> cloneMycelium(Tekton targetTekton) {
        return new SlowingMycelium(targetTekton);
    }

    @Override
    public Mycelium<SlowingSpore> cloneMycelium() {
        return new SlowingMycelium();
    }
}
