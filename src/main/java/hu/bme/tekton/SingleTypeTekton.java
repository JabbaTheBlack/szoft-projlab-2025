package hu.bme.tekton;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycologist;

/**
 * Represents a tekton where only one type of hyphae can grow, subclass of
 * Tekton.
 */
public class SingleTypeTekton extends Tekton {

    private Mycologist owner;

    /**
     * Initializes a new SingleTypeTekton, an empty list of neighbours and connected
     * neighbours.
     */
    public SingleTypeTekton() {
        super();
        owner = null;
    }

    /**
     * Adds a hyphae to the this tekton, only one type of hyphae can grow.
     * 
     * @param hyphae The hyphae to add.
     */
    @Override
    public boolean addHyphae(Hyphae hyphae) {
        if (fungalManager.getHyphaeCount() == 0) {
            this.owner = hyphae.getOwner();
            fungalManager.addHyphae(hyphae);
            return true;
        }
        if (hyphae.getOwner().equals(owner)) {
            fungalManager.addHyphae(hyphae);
            return true;
        }

        System.out.println("False");
        return false;
    }

    /**
     * Creates a new SingleTypeTekton
     */
    @Override
    public Tekton createTekton() {
        return new SingleTypeTekton();
    }
}
