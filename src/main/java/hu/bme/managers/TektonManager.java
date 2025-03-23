package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.tekton.Tekton;

/**
 * Manages a collection of tektons, providing methods for adding, removing, and retrieving them.
 */
public class TektonManager {
    
    private List<Tekton> tektons;

    /**
     * Initializes a new tekton manager with an empty list of tektons.
     */
    public TektonManager() {
        tektons = new ArrayList<>();
    }

    /**
     * Adds a tekton to the manager's collection.
     * @param tekton The tekton to be added.
     */
    public void addTekton(Tekton tekton) {
        tektons.add(tekton);
    }

    /**
     * Removes a tekton from the manager's collection.
     * @param tekton The tekton to be removed.
     */
    public void removeTekton(Tekton tekton) {
        tektons.remove(tekton);
    }

    /**
     * Returns the list of managed tektons.
     * @return The managed tektons.
     */
    public List<Tekton> getTektons() {
        return tektons;
    }

    public void breakApart(Tekton tekton) {
        System.out.println("[TektonManager] breakApart() -> ["+tekton+"]");
        List<Tekton> newTektons = tekton.breakApart();
        for (Tekton newTekton : newTektons) {
            //newTekton.refreshNeighbours();
            addTekton(newTekton);
        }
        removeTekton(tekton);
    }
}
