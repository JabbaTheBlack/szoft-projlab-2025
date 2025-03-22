package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Mycelium;
import hu.bme.interfaces.IMyceliumManager;

/**
 * Manages a collection of myceliums, providing methods for adding, removing, and counting them.
 */
public class MyceliumManager implements IMyceliumManager{
    private List<Mycelium> myceliums;

    /**
     * Initializes a new mycelium manager with an empty list of myceliums.
     */
    public MyceliumManager() {
        myceliums = new ArrayList<>();
    }

    /**
     * Adds a mycelium to the manager's collection.
     * @param mycelium The mycelium to be added.
     */
    @Override
    public void addMycelium(Mycelium mycelium) {
        myceliums.add(mycelium);
    }

    /**
     * Removes a mycelium from the manager's collection.
     * @param mycelium The mycelium to be removed.
     */
    @Override
    public void removeMycelium(Mycelium mycelium) {
        myceliums.remove(mycelium);
    }

    /**
     * Returns the number of myceliums managed.
     * @return The count of myceliums.
     */
    @Override
    public int getMyceliumCount() {
        return myceliums.size();
    }
}
