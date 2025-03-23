package hu.bme.interfaces;

import java.util.List;

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

    /**
     * Returns the number of spores
     * @return The number of spores
     */
    int getSporeCount();

    /**
     * Gets a list of spores
     * @return List of spores
     */
    List<Spore> getSpores();
}
