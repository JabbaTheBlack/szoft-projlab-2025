package hu.bme.tekton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Mycelium;

/**
 * Represents a tekton that is free of mycelium, subclass of Tekton.
 */
public class MyceliumFreeTekton extends Tekton{

    /**
     * Initializes a new MyceliumFreeTekton with an empty list of neighbours and connected neighbours.
     */
    public MyceliumFreeTekton() {
        super();
    }

    /**
     * Tries to add a mycelium to the tekton, will always fail.
     * @param mycelium The mycelium to add.
     */
    @Override
    public void addMycelium(Mycelium mycelium) {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'addMycelium' in MyceliumFreeTekton class");
    }

    @Override
    public List<Tekton> breakApart(){
        if(this.fungalManager.getMyceliumCount() == 0){
            MyceliumFreeTekton newTekton1 = new MyceliumFreeTekton();
            MyceliumFreeTekton newTekton2 = new MyceliumFreeTekton();

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
