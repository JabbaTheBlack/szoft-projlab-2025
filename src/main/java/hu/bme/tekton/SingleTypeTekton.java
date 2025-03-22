package hu.bme.tekton;

import hu.bme.fungi.Hyphae;

/**
 * Represents a tekton where only one type of hyphae can grow, subclass of Tekton.
 */
public class SingleTypeTekton extends Tekton {
    
    /**
     * Initializes a new SingleTypeTekton, an empty list of neighbours and connected neighbours.
     */
    public SingleTypeTekton() {
        super();
    }

    /**
     * Adds a hyphae to the this tekton, only one type of hyphae can grow.
     * @param hyphae The hyphae to add.
     */
    @Override
    public void addHyphae(Hyphae hyphae) {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'addHyphae' in SingelTypeTekton class");
    }
}
