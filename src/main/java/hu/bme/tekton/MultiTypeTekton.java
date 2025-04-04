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
     * Creates a new MulytiTypeTekton. 
    */
    @Override
    public Tekton createTekton() {
        return new MultiTypeTekton();
    }
}
