package hu.bme.fungi.mycelium;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.SpeedBoostSpore;
import hu.bme.tekton.Tekton;

public class SpeedBoostMycelium extends Mycelium<SpeedBoostSpore> {
    public SpeedBoostMycelium() {
        super();
    }

    public SpeedBoostMycelium(Tekton currentTekton) {
        super(currentTekton);
    }

    @Override
    public Mycelium<SpeedBoostSpore> cloneMycelium(Tekton targetTekton) {
        return new SpeedBoostMycelium(targetTekton);
    }

    @Override
    public Mycelium<SpeedBoostSpore> cloneMycelium() {
        return new SpeedBoostMycelium();
    }
}
