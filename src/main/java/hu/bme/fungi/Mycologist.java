package hu.bme.fungi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.SlowingSpore;
import hu.bme.fungi.spore.SpeedBoostSpore;
import hu.bme.fungi.spore.Spore;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.tekton.Tekton;

/**
 * Represents a mycologist who manages myceliums and interacts with Tekton.
 */
public class Mycologist {

    private List<Mycelium> myceliums;
    private List<Hyphae> hyphaes;

    /**
     * Initializes a new mycologist with an empty list of myceliums.
     */
    public Mycologist() {
        myceliums = new ArrayList<>();
        hyphaes = new ArrayList<>();
    }

    /**
     * Releases spores from the specified mycelium and manages its lifecycle.
     * If the mycelium exhausts its spore releases, it is disconnected from Tekton and its hyphae are removed.
     *
     * @param mycelium The mycelium to release spores from.
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
        
        Tekton neighbourTekton = null;
        boolean isNeighbour = false;

        // Get tektons neighbouring the target
        for(Tekton tekton : hyphae.getCurrentTekton()) {
            if(tekton.getNeighbours().contains(targetTekton)) {
                isNeighbour = true;
                neighbourTekton = tekton;
                break;
            }
        }
        if(isNeighbour == false) {
            System.out.println("[Mycologist] Failed to grow hyphae: Tekton is not a neighbour.");
            return;
        }

        //szomszédosak e a tektonok
        System.out.println("[Mycologist] new Hyphae() -> [Hyphae]");
        Hyphae newHyphae = new Hyphae();
        newHyphae.setOwner(hyphae.getOwner());

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Tekton]");
        if (!targetTekton.addHyphae(newHyphae)) {
            System.out.println("[Mycologist] Failed to grow hyphae: Tekton rejected it.");
            return;  
        }
        
        // ATNEZNI
        System.out.println("[Mycologist] setCurrentTekton(" + targetTekton + ") -> [Hyphae]");
        newHyphae.addCurrentTekton(targetTekton);

        System.out.println("[Mycologist] connectToTekton(" + targetTekton + ") -> [Tekton]");
        neighbourTekton.connectToTekton(targetTekton);

        System.out.println("[Mycologist] connectToTekton(" + hyphae.getCurrentTekton() + ") -> [Tekton]");
        targetTekton.connectToTekton(neighbourTekton);

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Hyphae]");
        hyphae.addHyphae(newHyphae);   
        
        hyphaes.add(newHyphae);

        //spore on the tekton, bc that it grows faster
        if(neighbourTekton.getSporeCount() >= 4) {
            Hyphae newHyphae2 = new Hyphae(targetTekton);
            
            System.out.println("[Mycologist]  addHyphae(" + newHyphae2 + ") -> [" + targetTekton + "]");
            if(targetTekton.addHyphae(newHyphae2)) {
                System.out.println("[Mycologist]  addHyphae(" + newHyphae2 + ") -> [" + newHyphae + "]");
                newHyphae.addHyphae(newHyphae2);
            }
        }

    }

    /**
     * Adds a mycelium to the list of managed myceliums.
     * @param mycelium The mycelium to be added.
     */
    public void addMycelium(Mycelium mycelium) {
        System.out.println("[Mycologist] addMycelium(" + mycelium + ") -> [Mycologist]");
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
        if(hyphae.getCurrentTekton().get(0) == targetTekton && hyphae.getCurrentTekton().size() == 1 && hyphae.getCurrentTekton().get(0).getSporeCount() >= 3) {
            System.out.println("[Mycologist] new Mycelium("+targetTekton+") -> [Mycelium]");
            Mycelium newMycelium = new Mycelium(targetTekton);
            System.out.println("[Mycologist] setCurrentTekton("+targetTekton+") -> ["+newMycelium+"]");
            
            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Tekton]");
            if(!targetTekton.addMycelium(newMycelium)) {
                return;
            }

            //remove spores
            for(int i = 0; i < 3; i++) {
                Spore spore = targetTekton.getSpores().get(0);
                System.out.println("[Mycologist] removeSpore(" + spore + ") -> [Tekton]");
                targetTekton.removeSpore(spore);
            }

            addMycelium(newMycelium);

            System.out.println("[Mycologist] addHyphae(" + hyphae + ") -> [Mycelium]");
            newMycelium.addHyphae(hyphae);

            System.out.println("[Mycologist] addMycelium(" + newMycelium + ") -> [Hyphae]");
            hyphae.addMycelium(newMycelium);
        } else {
            System.out.println("[Mycologist] Failed to grow mycelium: Hyphae is not on the target Tekton or there are not enough spores.");
        }
    }

    /**
     * Simulates the selection of a spore type by the user.
     * @param choice The choice of the user.
     */
    public void chooseSpore(int choice){
        System.out.println("[Mycoligist] new Mycelium() -> [Mycelium]");
        Mycelium<? extends Spore> mycelium;
        switch (choice) {
            case 1:
                mycelium = new Mycelium<StunSpore>();    
                break;
            case 2:
                mycelium = new Mycelium<DefensiveSpore>();
                // ((Mycelium<DefensiveSpore>) mycelium).addSpore(new DefensiveSpore());
                break;
            case 3:
                mycelium = new Mycelium<SpeedBoostSpore>();
                break;
            case 4:
                mycelium = new Mycelium<SlowingSpore>();
                break;
            default:
                System.out.println("Invalid choice.");
                return;  
        }
        this.addMycelium(mycelium);
    }

    /**
     * Grows a hyphae onto a Tetkton.
     * @param hyphae The hyphae from which the new will grow from
     * @param targetTekton The tekton on which the new hyphae will grow on
     */
    public void growHyphaeOnTekton(Hyphae hyphae, Tekton targetTekton) {
        if(hyphae.getCurrentTekton().size() != 2 || !hyphae.getCurrentTekton().contains(targetTekton)) {
            return;
        }
        
        Hyphae newHyphae = new Hyphae();
        if(targetTekton.addHyphae(newHyphae)) {
            newHyphae.addCurrentTekton(targetTekton);
            newHyphae.addHyphae(hyphae);
            newHyphae.setOwner(hyphae.getOwner());
            hyphae.addHyphae(newHyphae);
        }
    }

    public void growHyphaeOnTekton(Mycelium mycelium) {
        Hyphae newHyphae = new Hyphae(mycelium.getCurrentTekton());
        if(mycelium.getCurrentTekton().addHyphae(newHyphae)){
            newHyphae.setOwner(this);
            mycelium.addHyphae(newHyphae);
        }
        
    }

    /**
     * Simulates the consumption of an insect by the fungal network. If conditions are met,
     * adds a new Mycelium onto the insect's current Tekton and removes the insect from its environment.
     *
     * @param insect The insect to be consumed by the fungal network.
     */
    public void eatInsect(Insect insect) {
        HashSet<Tekton> tektons = new HashSet<>();
        for(Hyphae hyphae : hyphaes) {
            if(hyphae.getCurrentTekton().size() == 1) {
                tektons.add(hyphae.getCurrentTekton().get(0));
            }
        }
         
        if(insect.isStunned() && tektons.contains(insect.getCurrentTekton())) {
            
            Tekton insectTekton = insect.getCurrentTekton();
            Mycelium newMycelium = myceliums.get(0).clone();
            insectTekton.addMycelium(newMycelium);

            for(Hyphae hyphae : insectTekton.getHyphaes()){
                if(hyphae.getCurrentTekton().size() == 1) {
                    hyphae.addMycelium(newMycelium);
                    newMycelium.addHyphae(hyphae);
                }
            }
            
            insect.getEntomologist().removeInsect(insect);
            insect.setCurrentTekton(null); 
        }
    }

    /**
     * Removes a specific hyphae from the fungal network managed by this Mycologist.
     *
     * @param hyphae The hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        hyphae.removeHyphae(hyphae);
        hyphaes.remove(hyphae);
    }

    /**
     * Executes lifecycle management for all managed hyphaes. 
     */
    public void tick(){
        for(Hyphae hyphae : hyphaes) {
            hyphae.tick();
        }
    }

    /**
     * Retrieves all managed Myceliums in the fungal network.
     *
     * @return A list of all active Myceliums managed by this Mycologist.
     */
    public List<Mycelium> getMyceliums(){
        return myceliums;
    }

    public List<Hyphae> getHyphaes() {
        return hyphaes;
    }


}
