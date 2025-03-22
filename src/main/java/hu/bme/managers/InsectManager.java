package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.insect.Insect;

/**
 * Manages a collection of insects using the singleton pattern.
 */
public class InsectManager {
    
    private static volatile InsectManager instance;
    private List<Insect> insects;
    
    /**
     * Private constructor.
     */
    private InsectManager() {
        insects = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the InsectManager.
     * @return The instance of InsectManager.
     */
    public static InsectManager getInstance() {
        InsectManager result = instance;

        if(result == null) {
            synchronized(InsectManager.class) {
                result = instance;
                if(result == null) {
                    instance = result = new InsectManager();
                }
            }
        }
        return result;
    }
 
    /**
     * Adds an insect to the manager's collection.
     * @param insect The insect to be added.
     */
    public void addInsect(Insect insect) {
        insects.add(insect);
    }

    /**
     * Removes an insect from the manager's collection.
     * @param insect The insect to be removed.
     */
    public void removeInsect(Insect insect) {
        insects.remove(insect);
    }

    /**
     * Returns the number of insects managed.
     * @return The count of insects.
     */
    public int getInsectCount() {
        return insects.size();
    }
}
