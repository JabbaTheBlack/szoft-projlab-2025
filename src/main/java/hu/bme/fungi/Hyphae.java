package hu.bme.fungi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.bme.tekton.Tekton;


/**
 * Represents a hyphae in a fungal network, managing connections to other hyphae and myceliums.
 */
public class Hyphae{
    private List<Hyphae> connectedHyphae;
    private List<Mycelium> connectedMyceliums;
    private Tekton currentTekton;
    private Mycologist ownner;

    /**
     * Initializes a new hyphae with empty lists for connected hyphae and myceliums.
     */
    public Hyphae() {
        connectedHyphae = new ArrayList<>();
        connectedMyceliums = new ArrayList<>();
        currentTekton = null;
        ownner = new Mycologist();
    }

    public void setOwner(Mycologist owner) {
        this.ownner = owner;
    }

    public Mycologist getOwner() {
        return ownner;
    }

    /**
     * Checks if this hyphae is connected to any mycelium.
     * @return true if connected to at least one mycelium, false otherwise.
     */
    public boolean isConnectedToMycelium() {
        // TODO implement function
        throw new UnsupportedOperationException("Unimplemented method 'isConnectedToMycelium'");
    }

    /**
     * Adds a mycelium to the list of connected myceliums.
     * @param mycelium The mycelium to be added.
     */
    public void addMycelium(Mycelium mycelium) {
        connectedMyceliums.add(mycelium);
    }

    /**
     * Removes a mycelium from the list of connected myceliums.
     * @param mycelium The mycelium to be removed.
     */
    public void removeMycelium(Mycelium mycelium) {
        connectedMyceliums.remove(mycelium);
    }

    /**
     * Adds a hyphae to the list of connected hyphae.
     * @param hyphae The hyphae to be added.
     */
    public void addHyphae(Hyphae hyphae) {
        connectedHyphae.add(hyphae);
    }

    /**
     * Removes a hyphae from the list of connected hyphae.
     * @param hyphae The hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        connectedHyphae.remove(hyphae);
    }

     /**
     * Returns an unmodifiable list of connected hyphae.
     * @return List of connected hyphae.
     */
    public List<Hyphae> getConnectedHyphae() {
        return Collections.unmodifiableList(connectedHyphae);
    }

    /**
     * Returns an unmodifiable list of connected myceliums.
     * @return List of connected myceliums.
     */
    public List<Mycelium> getConnectedMyceliums() {
        return Collections.unmodifiableList(connectedMyceliums);
    }

    public Tekton getCurrentTekton() {
        return currentTekton;
    } 

    public void setCurrentTekton(Tekton currentTekton) {
        this.currentTekton = currentTekton;
    }
}
