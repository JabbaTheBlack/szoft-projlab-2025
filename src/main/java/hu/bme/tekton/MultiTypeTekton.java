package hu.bme.tekton;

import hu.bme.fungi.Hyphae;

/**
 * Represents a tekton where multiple hyphae can grow, subclass of Tekton.
 */
public class MultiTypeTekton extends Tekton {
    
    /**
     * Initializes a new MultiTypeTekton, an empty list of neighbours and connected neighbours.
     */
    public MultiTypeTekton() {
        super();
    }

    /**
     * Adds a hyphae to the fungal manager associated with this tekton.
     * @param hyphae The hyphae to add.
     */
    @Override
    public void addHyphae(Hyphae hyphae) {
        fungalManager.addHyphae(hyphae);
    }
}
