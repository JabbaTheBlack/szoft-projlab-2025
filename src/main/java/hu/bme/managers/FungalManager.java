package hu.bme.managers;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.Spore;
import hu.bme.interfaces.IHyphaeManager;
import hu.bme.interfaces.IMyceliumManager;
import hu.bme.interfaces.ISporeManager;

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
        hyphaeManager.removeHyphae(hyphae);
    }

    /**
     * Removes a spore from the manager.
     * @param spore The spore to be removed.
     */
    public void removeSpore(Spore spore) {
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
     * Checks if a specific hyphae is managed.
     * @param hyphae The hyphae to check for.
     * @return True if the hyphae is managed, false otherwise.
     */
    public boolean hasHyphae(Hyphae hyphae) {
        return hyphaeManager.hasHyphae(hyphae);
    }


    /**
     * Sets the spore manager.
     * @param sporeManager
     */
    public void setSporeManager(ISporeManager sporeManager) {
        this.sporeManager = sporeManager;
    }
}
