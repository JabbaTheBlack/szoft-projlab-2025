package hu.bme.interfaces;

import java.util.List;

import hu.bme.fungi.Hyphae;

/**
 * Interface for managing hyphaes withing the game
 * Provides methods for adding and removing hyphaes.
 */
public interface IHyphaeManager {
    /**
     * Adds a hyphae.
     * @param hyphae The hyphae to be added.
     */
    void addHyphae(Hyphae hyphae);

    /**
     * Removes a hyphae.
     * @param hyphae The hyphae to be removed.
     */
    void removeHyphae(Hyphae hyphae);

    /**
     * Returns the number of hyphaes.
     * @return The number of hyphaes.
     */
    int getHyphaeCount();

    /**
     * Checks if a manager has a given hyphae or not.
     * @param hyphae The hyphae to be checked.
     * @return true if the hyphae is managed, false otherwise.
     */
    boolean hasHyphae(Hyphae hyphae);

    /**
     * Returns a list of all managed hyphaes.
     * @return The list of managed hyphaes.
     */
    List<Hyphae> getHyphaes();
}
