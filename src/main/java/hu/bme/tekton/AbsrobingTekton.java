package hu.bme.tekton;

import hu.bme.console.ConsoleApp;
import hu.bme.fungi.Hyphae;
import java.util.Iterator;
import java.util.Map;


/**
 * Represents a tekton that can absorb hyphae, subclass of Tekton.
 */
public class AbsrobingTekton extends Tekton {
    private int absorbcount = 2;
    /**
     * Initializes a new AbsrobingTekton with an empty list of neighbours and connected neighbours.
     */
    public AbsrobingTekton() {
        super();
    }

    /**
     * Absorbs hyphae connected to this tekton.
     */
    @Override
    public void absorbHyphae() {
        if(absorbcount > 0){absorbcount--;}
        else {
            Iterator<Hyphae> iterator = fungalManager.getHyphaes().iterator();
            while (iterator.hasNext()) {
                Hyphae hyphae = iterator.next();
                //System.out.println("[AbsorbingTekton] removeHyphae(" + hyphae + ") -> [FungalManager]");
                iterator.remove();
                removeHyphae(hyphae);
                String id = null;
                for (Map.Entry<String, Hyphae> entry : ConsoleApp.hyphaesWithIds.entrySet()) {
                    if (entry.getValue() == hyphae) {
                        id = entry.getKey();
                    }
                }
            
                if(id != null) ConsoleApp.hyphaesWithIds.remove(id);
            }
            absorbcount = 2;
        }
    }

    /**
     * Creates new AbsrobingTekton instances using Prototype pattern.
     * @return Fresh AbsrobingTekton instance with default configuration
     */
    @Override
    public Tekton createTekton() {
        return new AbsrobingTekton();
    }
}
