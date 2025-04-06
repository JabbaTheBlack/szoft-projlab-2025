package hu.bme.tekton;

import hu.bme.fungi.Hyphae;
import java.util.Iterator;


/**
 * Represents a tekton that can absorb hyphae, subclass of Tekton.
 */
public class AbsrobingTekton extends Tekton {

    /**
     * Initializes a new AbsrobingTekton with an empty list of neighbours and connected neighbours.
     */
    public AbsrobingTekton() {
        super();
    }

    /**
     * Absorbs hyphae connected to this tekton.
     */
    public void absorbHyphae() {
        Iterator<Hyphae> iterator = fungalManager.getHyphaes().iterator();
        while (iterator.hasNext()) {
            Hyphae hyphae = iterator.next();
            System.out.println("[AbsorbingTekton] removeHyphae(" + hyphae + ") -> [FungalManager]");
            iterator.remove();
            removeHyphae(hyphae);
        }
    }

    /**
     * Creates new AbsrobingTekton instances using Prototype pattern.
     * @return Fresh AbsrobingTekton instance with default configuration
     */
    @Override
    public Tekton createTekton() {
        return new AbsrobingTekton();
    }
}
