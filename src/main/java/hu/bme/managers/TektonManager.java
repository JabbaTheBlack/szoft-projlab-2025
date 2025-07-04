package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.interfaces.ITickable;
import hu.bme.tekton.Tekton;

/**
 * Manages a collection of tektons, providing methods for adding, removing, and
 * retrieving them.
 */
public class TektonManager implements ITickable{
    private static volatile TektonManager instance;
    private List<Tekton> tektons;
    private int breakApartCounter = 0;

    /**
     * Initializes a new tekton manager with an empty list of tektons.
     */
    private TektonManager() {

        tektons = new ArrayList<>();

    }

    public static TektonManager getInstance() {
        TektonManager result = instance;

        if (result == null) {
            synchronized (TektonManager.class) {
                result = instance;

                if (result == null) {
                    instance = result = new TektonManager();
                }
            }
        }

        return result;
    }

    /**
     * Adds a tekton to the manager's collection.
     * 
     * @param tekton The tekton to be added.
     */
    public void addTekton(Tekton tekton) {
        tektons.add(tekton);
    }

    /**
     * Removes a tekton from the manager's collection.
     * 
     * @param tekton The tekton to be removed.
     */
    public void removeTekton(Tekton tekton) {
        tektons.remove(tekton);
    }

    /**
     * Returns the list of managed tektons.
     * 
     * @return The managed tektons.
     */
    public List<Tekton> getTektons() {
        return tektons;
    }

    /**
     * This method creates new tektons from the original one and adds them to the
     * manager's collection.
     * After that refreshes the neighbours of the new tektons and removes the
     * original tekton from the collection.
     * 
     * @param tekton to be broken apart.
     */
    public List<Tekton> breakApart(Tekton tekton) {
        breakApartCounter++;
        List<Tekton> newTektons = null;
        if (breakApartCounter > 0) {
            System.out.println("[TektonManager] breakApart() -> [" + tekton + "]");
            newTektons = tekton.breakApart();    
        }
        

        if (newTektons == null ) {
            System.out.println("[TektonManager] breakApart() <- [" + tekton + "] {fail}");
            return null;
        }
        for (Tekton newTekton : newTektons) {
            System.out.println("[TektonManager] refreshNeighbour() -> [" + newTekton + "]");
            newTekton.refreshNeighbours();
            addTekton(newTekton);
        }
        removeTekton(tekton);
        breakApartCounter = 0;
        
        return newTektons;
    }

    @Override
    public void tick() {
        for(Tekton tekton : tektons) {
            tekton.tick();
        }
    }
}
