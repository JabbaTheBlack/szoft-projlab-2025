package hu.bme.tekton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycologist;

/**
 * Represents a tekton where only one type of hyphae can grow, subclass of Tekton.
 */
public class SingleTypeTekton extends Tekton {
    
    private Mycologist owner;

    
    /**
     * Initializes a new SingleTypeTekton, an empty list of neighbours and connected neighbours.
     */
    public SingleTypeTekton() {
        super();
        owner = null;
    }

    /**
     * Adds a hyphae to the this tekton, only one type of hyphae can grow.
     * @param hyphae The hyphae to add.
     */
    @Override
    public boolean addHyphae(Hyphae hyphae) {
        // TODO implement function, add javadoc
        if(fungalManager.getHyphaeCount() == 0) {
            this.owner = hyphae.getOwner();
            fungalManager.addHyphae(hyphae);
            return true;
        } 
        if (hyphae.getOwner().equals(owner) && owner != null) {
            fungalManager.addHyphae(hyphae);
            return true;
        }
        
        System.out.println("False");
        return false;
    }

    @Override
    public List<Tekton> breakApart(){
        if(this.fungalManager.getMyceliumCount() == 0){
            SingleTypeTekton newTekton1 = new SingleTypeTekton();
            SingleTypeTekton newTekton2 = new SingleTypeTekton();

            newTekton1.addNeighbour(newTekton2);
            newTekton2.addNeighbour(newTekton1);
            this.fungalManager.getHyphaes().forEach(hyphae -> {
                if(hyphae.getCurrentTekton().size() >= 2){
                    newTekton1.addHyphae(hyphae);
                    hyphae.addCurrentTekton(newTekton1);
                    hyphae.removeCurrentTekton(this);
                } else{
                    hyphae.getConnectedHyphae().forEach(nghHyphae -> {
                        nghHyphae.removeHyphae(hyphae);
                    });
                }
            });
            this.fungalManager.getSpores().forEach(spore -> {
                newTekton1.addSpore(spore);
            });

            List<Tekton> newTektons = new ArrayList<>();
            newTektons.add(newTekton1);
            newTektons.add(newTekton2);
            
            return newTektons;
        }
        return null;
    }
}
