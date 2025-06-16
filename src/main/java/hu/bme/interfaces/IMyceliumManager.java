package hu.bme.interfaces;

import java.util.List;

import hu.bme.fungi.Mycelium;

/**
 * Interface for managing myceliums withing the game
 * Provides methods for adding and removing myceliums.
 */
public interface IMyceliumManager {
    /**
     * Adds a mycelium.
     * @param mycelium The mycelium to be added.
     */
    void addMycelium(Mycelium mycelium);

    /**
     * Removes a mycelium.
     * @param mycelium The mycelium to be removed.
     */
    void removeMycelium(Mycelium mycelium);

    /**
     * Returns the number of myceliums.
     * @return The number of myceliums.
     */
    int getMyceliumCount();

    /**
     * Returns the myceliums in a List.
     * @return The list of myceliums.
     */
    List<Mycelium> getMyceliums();
}
