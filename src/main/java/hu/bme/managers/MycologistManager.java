package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Mycologist;

/**
 * Manages a collection of mycologists using the singleton pattern.
 */
public class MycologistManager {
    private static volatile MycologistManager instance;
    private List<Mycologist> mycologists;

    /**
     * Private MycologistManager constructor.
     */
    private MycologistManager() {
        mycologists = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the MycologistManager.
     * @return The instance of MycologistManager.
     */
    public MycologistManager getInstance() {
        MycologistManager result = instance;

        if(result == null) {
            synchronized(MycologistManager.class) {
                result = instance;
                if(result == null) {
                    instance = result = new MycologistManager();
                }
            }
        }
        return result;
    }

    /**
     * Adds a mycologist to the manager's collection.
     * @param mycologist The mycologist to be added.
     */
    public void addMycologist(Mycologist mycologist) {
        mycologists.add(mycologist);
    }

     /**
     * Removes a mycologist from the manager's collection.
     * @param mycologist The mycologist to be removed.
     */
    public void removeMycologist(Mycologist mycologist) {
        mycologists.remove(mycologist);
    }

    /**
     * Returns the number of mycologists managed.
     * @return The count of mycologists.
     */
    public int getMycologistCount() {
        return mycologists.size();
    }
}
