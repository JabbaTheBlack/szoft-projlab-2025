package hu.bme.tekton;

import java.util.List;
import java.util.ArrayList;

import hu.bme.fungi.Hyphae;
import java.util.Iterator;


/**
 * Represents a tekton that can absorb hyphae, subclass of Tekton.
 */
public class AbsrobingTekton extends Tekton {
    
    private float absrobingRate;

    /**
     * Initializes a new AbsrobingTekton with an empty list of neighbours and connected neighbours.
     */
    public AbsrobingTekton() {
        super();
    }

    /**
     * Absorbs hyphae connected to this tekton.
     */
    public void absorbHyphae() {
        Iterator<Hyphae> iterator = fungalManager.getHyphaes().iterator();
        while (iterator.hasNext()) {
            Hyphae hyphae = iterator.next();
            System.out.println("[AbsorbingTekton] removeHyphae(" + hyphae + ") -> [FungalManager]");
            iterator.remove();
            removeHyphae(hyphae);
        }
        // TODO add javadoc
    }

    @Override
    public List<Tekton> breakApart(){
        if(this.fungalManager.getMyceliumCount() == 0){
            AbsrobingTekton newTekton1 = new AbsrobingTekton();
            AbsrobingTekton newTekton2 = new AbsrobingTekton();

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
