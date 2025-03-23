package hu.bme.interfaces;

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

    boolean hasHyphae(Hyphae hyphae);
}
