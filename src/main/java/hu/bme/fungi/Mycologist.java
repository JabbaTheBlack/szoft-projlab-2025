package hu.bme.fungi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


import hu.bme.fungi.spore.Spore;
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
        System.out.println("[Mycologist] releaseSpore() -> [" + mycelium + "]");
        mycelium.releaseSpores();

        if(mycelium.getRemainingSporeReleases() == 0) {
            System.out.println("[Mycologist] setCurrentTekton() -> [" + mycelium + "]");
            mycelium.setCurrentTekton(null);

            System.out.println("[Mycologist] removeAllHyphae() -> [" + mycelium + "]");
            mycelium.removeAllHyphae();
        }
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
        newHyphae.setOwner(hyphae.getOwner()); 

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Tekton]");
        if (!targetTekton.addHyphae(newHyphae)) {
            System.out.println("[Mycologist] Failed to grow hyphae: Tekton rejected it.");
            return;  
        }

        System.out.println("[Mycologist] setCurrentTekton(" + targetTekton + ") -> [Hyphae]");
        newHyphae.setCurrentTekton(null);

        System.out.println("[Mycologist] connectToTekton(" + targetTekton + ") -> [Tekton]");
        hyphae.getCurrentTekton().connectToTekton(targetTekton);

        System.out.println("[Mycologist] connectToTekton(" + hyphae.getCurrentTekton() + ") -> [Tekton]");
        targetTekton.connectToTekton(hyphae.getCurrentTekton());

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Hyphae]");
        hyphae.addHyphae(newHyphae);

        if(hyphae.getCurrentTekton().getSporeCount() >= 4) {
            Hyphae newHyphae2 = new Hyphae(targetTekton);
            
            System.out.println("[Mycologist]  addHyphae(" + newHyphae2 + ") -> [" + targetTekton + "]");
            if(targetTekton.addHyphae(newHyphae2)) {
                System.out.println("[Mycologist]  addHyphae(" + newHyphae2 + ") -> [" + newHyphae + "]");
                newHyphae.addHyphae(newHyphae2);
                Random random = new Random();
                List<Spore> spores = new ArrayList<>(hyphae.getCurrentTekton().getSpores());
                
                Collections.shuffle(spores, random);

                for(int i = 0; i < 4; i++) {
                    Spore spore = spores.get(i);  
                    System.out.println("[Mycologist]  getCurrentTekton() -> [" + hyphae + "]");
                    System.out.println("[Mycologist]  getCurrentTekton() <- [" + hyphae + "]{" + hyphae.getCurrentTekton() + "}");
                    System.out.println("[Hyphae]  removeSpore() -> [" + spore + "]");
                    
                    hyphae.getCurrentTekton().removeSpore(spore);             
                }
            }
        }

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
        if(hyphae.getCurrentTekton() == targetTekton) {
            Mycelium newMycelium = new Mycelium(targetTekton);

            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Tekton]");
            if(!targetTekton.addMycelium(newMycelium)) {
                return;
            }

            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Mycologist]");
            addMycelium(newMycelium);

            System.out.println("[Mycologist] addHyphae(" + hyphae + ") -> [Mycelium]");
            newMycelium.addHyphae(hyphae);

            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Hyphae]");
            hyphae.addMycelium(newMycelium);

               
        }
    }
}
