package hu.bme.fungi;

import hu.bme.tekton.Tekton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.spore.*;

/**
 * Represents a mycelium in a fungal network, managing spores and hyphae.
 */
public class Mycelium {
    
    private boolean upgraded;
    private List<Spore> spores;
    private List<Hyphae> hyphaes;
    private Tekton currentTekton;

    /**
     * Initializes a new mycelium with empty lists for spores, hyphae, unupgraded.
     */
    public Mycelium() {
        upgraded = false;
        spores = new ArrayList<>();
        hyphaes = new ArrayList<>();
        currentTekton = null;
    }

    public Mycelium(Tekton currentTekton) {
        this();
        this.currentTekton = currentTekton;
    }

    /**
     * Checks if the mycelium is upgraded.
     * @return true if the mycelium is upgraded, false otherwise.
     */
    public boolean isUpgraded() { return upgraded; }

    /**
     * Upgrades the mycelium.
     */
    public void upgrade() {
        upgraded = true;
    }

    /**
     * Adds more spores to the mycelium.
     */
    public void growSpores(){
        // TODO implement function
    }

    /**
     * Releases spores from the mycelium.
     */
    public void releaseSpores() {
        // TODO implement function
    }

    /**
     * Sets the current tekton of the mycelium.
     */
    public void setCurrentTekton(Tekton tekton) {
        currentTekton = tekton;
    }

    /**
     * Increment the spore count of the mycelium by one.
     */
    public void addSpore(Spore spore) {
        spores.add(spore);
    }

    /**
     * Decrement the spore count of the mycelium by one.
     */
    public void removeSpore(Spore spore) {
        spores.remove(spore);
    }
    
    /**
     * Adds a hyphae to the mycelium.
     * @return the hyphae to be added.
     */
    public void addHyphae(Hyphae hyphae) {
        hyphaes.add(hyphae);
    }

    /**
     * Removes a hyphae from the mycelium.
     * @return the hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        hyphaes.remove(hyphae);
    }
}
