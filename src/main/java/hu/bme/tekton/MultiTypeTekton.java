package hu.bme.tekton;

import java.util.ArrayList;
import java.util.List;

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
    public boolean addHyphae(Hyphae hyphae) {
        if(!hasHyphae(hyphae)) {
            fungalManager.addHyphae(hyphae);
            return true;
        }
        return false;   
    }

    @Override
    public Tekton createTekton() {
        return new MultiTypeTekton();
    }
}
