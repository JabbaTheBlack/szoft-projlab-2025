package hu.bme.tekton;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycologist;

import java.util.Iterator;
import java.util.Map;

/**
 * Represents a tekton that can absorb hyphae, subclass of Tekton.
 */
public class AbsrobingTekton extends Tekton {
    private int nextAbsorb = 2;

    /**
     * Initializes a new AbsrobingTekton with an empty list of neighbours and
     * connected neighbours.
     */
    public AbsrobingTekton() {
        super();
    }

    /**
     * Absorbs hyphae connected to this tekton.
     */
    @Override
    public void absorbHyphae() {
        if (nextAbsorb > 0) {
            nextAbsorb--;
        } else {
            Iterator<Hyphae> iterator = fungalManager.getHyphaes().iterator();
            while (iterator.hasNext()) {
                Hyphae hyphae = iterator.next();
                if(hyphae.getCurrentTekton().size() != 2) {
                    iterator.remove();
                    hyphae.getOwner().removeHyphae(hyphae);;
                    removeHyphae(hyphae);
                }
            }
            nextAbsorb = 2;
        }
    }

    /**
     * Creates new AbsrobingTekton instances using Prototype pattern.
     * 
     * @return Fresh AbsrobingTekton instance with default configuration
     */
    @Override
    public Tekton createTekton() {
        return new AbsrobingTekton();
    }

    @Override
    public void tick() {
        absorbHyphae();
    }
}
