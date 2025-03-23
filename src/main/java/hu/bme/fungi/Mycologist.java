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
     * Grows hyphae from a mycelium towards a Tekton.
     * @param hyphae The hyphae to be grown.
     * @param targetTekton The Tekton to grow the hyphae to.
     */
    public void growHyphaeToTekton(Hyphae hyphae, Tekton targetTekton) {
        
        System.out.println("[Mycologist] new Hyphae() -> [Mycologist]");
        Hyphae newHyphae = new Hyphae();
        newHyphae.setOwner(hyphae.getOwner());

        if(!hyphae.getCurrentTekton().get(0).isConnectedTo(targetTekton)) {
            System.out.println("[Mycologist] <- [targetTekton] {fail}");
            return;
        }

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Tekton]");
        if (!targetTekton.addHyphae(newHyphae)) {
            System.out.println("[Mycologist] Failed to grow hyphae: Tekton rejected it.");
            return;  
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

        if(hyphae.getCurrentTekton().get(0).getSporeCount() >= 4) {
            Hyphae newHyphae2 = new Hyphae(targetTekton);
            
            System.out.println("[Mycologist]  addHyphae(" + newHyphae2 + ") -> [" + targetTekton + "]");
            if(targetTekton.addHyphae(newHyphae2)) {
                System.out.println("[Mycologist]  addHyphae(" + newHyphae2 + ") -> [" + newHyphae + "]");
                newHyphae.addHyphae(newHyphae2);
                Random random = new Random();
                List<Spore> spores = new ArrayList<>(hyphae.getCurrentTekton().get(0).getSpores());
                
                Collections.shuffle(spores, random);

                for(int i = 0; i < 4; i++) {
                    Spore spore = spores.get(i);  
                    System.out.println("[Mycologist]  getCurrentTekton() -> [" + hyphae + "]");
                    System.out.println("[Mycologist]  getCurrentTekton() <- [" + hyphae + "]{" + hyphae.getCurrentTekton() + "}");
                    System.out.println("[Hyphae]  removeSpore() -> [" + spore + "]");
                    
                    hyphae.getCurrentTekton().get(0).removeSpore(spore);             
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

    /**
     * Grows mycelium from a hyphae on a given Tekton.
     * @param hyphae The hyphae to be grown.
     * @param targetTekton The Tekton to grow the mycelium on.
     */
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
