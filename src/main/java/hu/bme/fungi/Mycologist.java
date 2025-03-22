package hu.bme.fungi;

import java.util.ArrayList;
import java.util.List;

import hu.bme.tekton.Tekton;

/**
 * Represents a mycologist who manages myceliums and interacts with Tekton.
 */
public class Mycologist {

    private List<Mycelium> myceliums;

    /**
     * Initializes a new mycologist with an empty list of myceliums.
     */
    public Mycologist() {
        myceliums = new ArrayList<>();
    }

    /**
     * Releases spores from a mycelium.
     * @param mycelium
     */
    public void releaseSpore(Mycelium mycelium) {   
        mycelium.releaseSpores();
    }

    /**
     * Upgrades a mycelium.
     * @param mycelium the mycelium to be upgraded
     */
    public void upgradeMyceium(Mycelium mycelium) {
        mycelium.upgrade();
    }

    /**
     * Grows hyphae towards a Tekton.
     * @param tekton The current Tekton.
     * @param tekton The target Tekton towards which hyphae are grown.
     */
    public void growHyphaeToTekton(Hyphae hyphae, Tekton targetTekton) {
        // TODO add javadoc
        
        System.out.println("[Mycologist] new Hyphae() -> [Mycologist]");
        Hyphae newHyphae = new Hyphae();
        newHyphae.setOwner(hyphae.getOwner()); // Ensure owner consistency

        if(!hyphae.getCurrentTekton().get(0).isConnectedTo(targetTekton)) {
            System.out.println("[Mycologist] <- [targetTekton] {fail}");
            return;
        }

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Tekton]");
        if (!targetTekton.addHyphae(newHyphae)) {
            System.out.println("[Mycologist] Failed to grow hyphae: Tekton rejected it.");
            return;  // Stop execution if adding hyphae fails
        }
        
        // ATNEZNI
        System.out.println("[Mycologist] setCurrentTekton(" + targetTekton + ") -> [Hyphae]");
        newHyphae.addCurrentTekton(targetTekton);

        System.out.println("[Mycologist] connectToTekton(" + targetTekton + ") -> [Tekton]");
        hyphae.getCurrentTekton().get(0).connectToTekton(targetTekton);

        System.out.println("[Mycologist] connectToTekton(" + hyphae.getCurrentTekton() + ") -> [Tekton]");
        targetTekton.connectToTekton(hyphae.getCurrentTekton().get(0));

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Hyphae]");
        hyphae.addHyphae(newHyphae);
        

    }

    /**
     * Adds a mycelium to the list of managed myceliums.
     * @param mycelium The mycelium to be added.
     */
    public void addMycelium(Mycelium mycelium) {
        myceliums.add(mycelium);
    }

    /**
     * Removes a mycelium from the list of managed myceliums, when a mycelium is dies.
     * @param mycelium The mycelium to be removed.
     */
    public void removeMycelium(Mycelium mycelium) {
        myceliums.remove(mycelium);    
    }
    
}
