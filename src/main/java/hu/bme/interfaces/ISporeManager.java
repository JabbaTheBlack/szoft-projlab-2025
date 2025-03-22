package hu.bme.interfaces;

import java.lang.reflect.Array;
import java.util.ArrayList;

import hu.bme.fungi.spore.Spore;

/**
 * Interface for managing spores withing the game
 * Provides methods for adding and removing spores.
 */
public interface ISporeManager {
    /**
     * Adds a spore
     * @param spore Spore to be added
     */
    void addSpore(Spore spore);

    /**
     * Removes a spore
     * @param spore Spore to be removed
     */
    void removeSpore(Spore spore);

    int getSporeCount();
    ArrayList<Spore> getSpores();
}
