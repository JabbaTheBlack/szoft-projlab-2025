package hu.bme.tekton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Hyphae;

/**
 * Represents a tekton where multiple hyphae can grow, subclass of Tekton.
 */
public class MultiTypeTekton extends Tekton {
    
    /**
     * Initializes a new MultiTypeTekton, an empty list of neighbours and connected neighbours.
     */
    public MultiTypeTekton() {
        super();
    }

    /**
     * Adds a hyphae to the fungal manager associated with this tekton.
     * @param hyphae The hyphae to add.
     */
    @Override
    public boolean addHyphae(Hyphae hyphae) {
        if(!hasHyphae(hyphae)) {
            fungalManager.addHyphae(hyphae);
            return true;
        }
        return false;   
    }

    @Override
    public List<Tekton> breakApart(){
        if(this.fungalManager.getMyceliumCount() == 0){
            
            MultiTypeTekton newTekton1 = new MultiTypeTekton();
            System.out.println("["+this+"] new() - -> ["+newTekton1+"]");
            MultiTypeTekton newTekton2 = new MultiTypeTekton();
            System.out.println("["+this+"] new() - -> ["+newTekton2+"]");


            newTekton1.addNeighbour(newTekton2);
            newTekton2.addNeighbour(newTekton1);

            this.fungalManager.getHyphaes().forEach(hyphae -> {
                if(hyphae.getCurrentTekton().size() >= 2){
                    System.out.println("["+this+"] addHyphea("+hyphae+") -> ["+newTekton1+"]");
                    newTekton1.addHyphae(hyphae);
                    System.out.println("["+this+"] addCurrentTekton("+newTekton1+") -> ["+hyphae+"]");
                    hyphae.addCurrentTekton(newTekton1);
                    System.out.println("["+this+"] removeCurrentTekton("+this+") -> ["+hyphae+"]");
                    hyphae.removeCurrentTekton(this);
                } else{
                    hyphae.getConnectedHyphae().forEach(nghHyphae -> {
                        System.out.println("["+this+"] removeHyphae("+hyphae+") -> ["+nghHyphae+"]");
                        nghHyphae.removeHyphae(hyphae);
                    });
                }
            });
            this.fungalManager.getSpores().forEach(spore -> {
                System.out.println("["+this+"] addSpore("+spore+") -> ["+newTekton1+"]");
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
