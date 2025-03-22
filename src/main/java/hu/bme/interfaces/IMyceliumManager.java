package hu.bme.interfaces;

import java.lang.reflect.Array;
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

    int getMyceliumCount();

    List<Mycelium> getMyceliums();
}
