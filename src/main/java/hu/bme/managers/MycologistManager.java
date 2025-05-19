package hu.bme.managers;

import java.util.ArrayList;

import hu.bme.fungi.Mycologist;
import hu.bme.interfaces.ITickable;

/**
 * Manages a collection of mycologists using the singleton pattern.
 */
public class MycologistManager implements ITickable{
    private static volatile MycologistManager instance;
    private ArrayList<Mycologist> mycologists;

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
    public static MycologistManager getInstance() {
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

    //TODO javadoc
    public void tick() {
        for(Mycologist mycologist : mycologists) {
            mycologist.tick();
        }
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

    public ArrayList<Mycologist> getMycologists() {
        return mycologists;
    }
}
