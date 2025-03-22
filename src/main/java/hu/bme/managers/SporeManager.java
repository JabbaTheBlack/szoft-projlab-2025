package hu.bme.managers;

import hu.bme.interfaces.ISporeManager;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.spore.*;

/**
 * Manages a collection of spores, providing methods for adding, removing, and counting them.
 */
public class SporeManager implements ISporeManager{
    private ArrayList<Spore> spores;

    /**
     * Initializes a new spore manager with an empty list of spores.
     */
    public SporeManager() {
        spores = new ArrayList<>();
    }

    /**
     * Adds a spore to the manager's collection.
     * @param spore The spore to be added.
     */
    @Override
    public void addSpore(Spore spore) {
        spores.add(spore);
    }

    /**
     * Removes a spore from the manager's collection.
     * @param spore The spore to be removed.
     */
    @Override
    public void removeSpore(Spore spore) {
        spores.remove(spore);
    }

    /**
     * Returns the number of spores managed.
     * @return The count of spores.
     */
    @Override 
    public int getSporeCount() {
        return spores.size();
    }

    @Override
    public List<Spore> getSpores() {
        return spores;
    }
}
