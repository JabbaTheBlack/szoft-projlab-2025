package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.interfaces.IHyphaeManager;

/**
 * Manages a collection of hyphae, providing methods for adding, removing, and checking their existence.
 */
public class HyphaeManager implements IHyphaeManager {
    
    private ArrayList<Hyphae> hyphaes;

    /**
     * Initializes a new hyphae manager with an empty list of hyphae.
     */
    public HyphaeManager() {
        hyphaes = new ArrayList<>();
    }

     /**
     * Adds a hyphae to the manager's collection.
     * @param hyphae The hyphae to be added.
     */
    @Override
    public void addHyphae(Hyphae hyphae) {
        hyphaes.add(hyphae);
    }

    /**
     * Removes a hyphae from the manager's collection.
     * @param hyphae The hyphae to be removed.
     */
    @Override
    public void removeHyphae(Hyphae hyphae) {
        hyphaes.remove(hyphae);
    }    

    /**
     * Returns the number of hyphae managed.
     * @return The count of hyphae.
     */
    @Override
    public int getHyphaeCount() {
        return hyphaes.size();
    }

    /**
     * Checks if a specific hyphae is managed.
     * @param hyphae The hyphae to check for.
     * @return True if the hyphae is managed, false otherwise.
     */
    @Override
    public boolean hasHyphae(Hyphae hyphae) {
        return hyphaes.contains(hyphae);
    }

    /**
     * Returns a list of all managed hyphae.
     * @return The list of managed hyphae.
     */
    @Override
    public List<Hyphae> getHyphaes() {
        return hyphaes;
    }
}
