package hu.bme.fungi;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.spore.SporeEffect;
import hu.bme.tekton.Tekton;

/**
 * Represents a mycologist who manages myceliums and interacts with Tekton.
 */
public class Mycologist {

    private SporeEffect selectedSporeEffect;
    private List<Mycelium> myceliums;

    /**
     * Initializes a new mycologist with an empty list of myceliums.
     */
    public Mycologist() {
        myceliums = new ArrayList<>();
    }

    /**
     * Chooses a spore effect to be used.
     * @param sporeEffect the spore effect to be chosen
     */
    public void chooseSporeEffect(SporeEffect sporeEffect) {
        selectedSporeEffect = sporeEffect;
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
    public void growHyphaeToTekton(Tekton tekton) {
        // TODO implement function
        throw new UnsupportedOperationException("Unimplemented method 'growHyphaeToTekton' in Mycologist class");
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
    
    public void growMycelium(Hyphae hyphae, Tekton targetTekton) {

        if(hyphae.getCurrentTekton() != targetTekton) {
            Mycelium newMycelium = new Mycelium(targetTekton);
            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Mycologist]");
            addMycelium(newMycelium);

            System.out.println("[Mycologist] addHyphae(" + hyphae + ") -> [Mycelium]");
            newMycelium.addHyphae(hyphae);

            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Hyphae]");
            hyphae.addMycelium(newMycelium);

            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Tekton]");
            targetTekton.addMycelium(newMycelium);
        }
    }

}
