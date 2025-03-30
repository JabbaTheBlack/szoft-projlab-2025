package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.interfaces.ITickable;

/**
 * Manages a collection of insects using the singleton pattern.
 */
public class InsectManager implements ITickable{
    
    private static volatile InsectManager instance;
    private List<Entomologist> entomologists;
    
    /**
     * Private constructor.
     */
    private InsectManager() {
        entomologists = new ArrayList<>();
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
    public void addEntomologist(Entomologist entomologist) {
        entomologists.add(entomologist);
    }

    /**
     * Removes an insect from the manager's collection.
     * @param insect The insect to be removed.
     */
    public void removeEntomologist(Entomologist entomologist) {
        entomologists.remove(entomologist);
    }

    /**
     * Returns the number of insects managed.
     * @return The count of insects.
     */
    public int getEntomologistCount() {
        return entomologists.size();
    }

    @Override
    public void tick() {
        for(Entomologist entomologist : entomologists){
            entomologist.tick();
        }
    }
}
