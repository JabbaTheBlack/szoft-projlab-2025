package hu.bme.tekton;

import hu.bme.fungi.Mycelium;


/**
 * Represents a tekton that is free of mycelium, subclass of Tekton.
 */
public class MyceliumFreeTekton extends Tekton {

    /**
     * Initializes a new MyceliumFreeTekton with an empty list of neighbours and
     * connected neighbours.
     */
    public MyceliumFreeTekton() {
        super();
    }

    /**
     * Tries to add a mycelium to the tekton, will always fail.
     * 
     * @param mycelium The mycelium to add.
     */
    @Override
    public boolean addMycelium(Mycelium mycelium) {
        return false;
    }

    /**
     * Creates a new MyceliumFreeTekton
     */
    @Override
    public Tekton createTekton() {
        return new MyceliumFreeTekton();
    }
}
