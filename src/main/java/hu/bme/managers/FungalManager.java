package hu.bme.managers;

import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.Spore;
import hu.bme.interfaces.IHyphaeManager;
import hu.bme.interfaces.IMyceliumManager;
import hu.bme.interfaces.ISporeManager;


import java.util.List;




/**
 * Manages fungal components such as myceliums, hyphae, and spores.
 */
public class FungalManager {
    private IMyceliumManager myceliumManager;
    private IHyphaeManager hyphaeManager;
    private ISporeManager sporeManager;

    /**
     * Initializes a new fungal manager with default managers for myceliums, hyphae, and spores.
     */
    public FungalManager() {
        myceliumManager = new MyceliumManager();
        hyphaeManager = new HyphaeManager();
        sporeManager = new SporeManager();
    }

    /**
     * Adds a mycelium to the manager.
     * @param mycelium The mycelium to be added.
     */
    public void addMycelium(Mycelium mycelium) {
        myceliumManager.addMycelium(mycelium);
    }

    /**
     * Adds a hyphae to the manager.
     * @param hyphae The hyphae to be added.
     */
    public void addHyphae(Hyphae hyphae) {
        hyphaeManager.addHyphae(hyphae);
    }

    /**
     * Adds a spore to the manager.
     * @param spore The spore to be added.
     */
    public void addSpore(Spore spore) {
        sporeManager.addSpore(spore);
    }

    /**
     * Removes a mycelium from the manager.
     * @param mycelium The mycelium to be removed.
     */
    public void removeMycelium(Mycelium mycelium) {
        myceliumManager.removeMycelium(mycelium);
    }

    /**
     * Removes a hyphae from the manager.
     * @param hyphae The hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        System.out.println("[FungalManager] removeHyphae("+ hyphae +") -> [HyphaeManager]");
        for (Hyphae rmv : getHyphaes()) {
            System.out.println("[HyphaeManager] removeHyphae("+ hyphae +") -> [" + rmv + "]");
            rmv.removeHyphae(hyphae);
        }
        System.out.println("[FungalManager] removeHyphae("+ hyphae +") -> [MyceliumManager]");
        for (Mycelium rmv : getMyceliums()) {
            System.out.println("[MyceliumManager] removeHyphae("+ hyphae +") -> [" + rmv + "]");
            rmv.removeHyphae(hyphae);
            
        }
        
        hyphaeManager.removeHyphae(hyphae);
    }

    /**
     * Removes a spore from the manager.
     * @param spore The spore to be removed.
     */
    public void removeSpore(Spore spore) {
        System.out.println("[FungalManager] removeSpore("+ spore +") -> [SporeManager]");
        sporeManager.removeSpore(spore);    
    }

    /**
     * Returns the number of myceliums managed.
     * @return The count of myceliums.
     */
    public int getMyceliumCount() {
        return myceliumManager.getMyceliumCount();
    }

    /**
     * Returns the number of spores managed.
     * @return The count of spores.
     */
    public int getHyphaeCount() {
        return hyphaeManager.getHyphaeCount();
    }

    /**
     * Returns the number of spores managed.
     * @return The count of spores.
     */
    public int getSporeCount() {
        return sporeManager.getSporeCount();
    }

    /**
     * Checks if a specific hyphae is managed.
     * @param hyphae The hyphae to check for.
     * @return True if the hyphae is managed, false otherwise.
     */
    public boolean hasHyphae(Hyphae hyphae) {
        return hyphaeManager.hasHyphae(hyphae);
    }

    /**
     * Gets a list of spores
     * @return List of spores
     */
    public List<Spore> getSpores() {
        return sporeManager.getSpores();
    }

    /**
     * Gets a list of hyphaes
     * @return List of hyphaes
     */
    public List<Hyphae> getHyphaes() {
        return hyphaeManager.getHyphaes();
    }

    /**
     * Gets a list of myceliums
     * @return List of myceliums
     */
    public List<Mycelium> getMyceliums() {
        return myceliumManager.getMyceliums();
    } 
}
