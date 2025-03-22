package hu.bme.tekton;

import java.util.List;
import java.util.ArrayList;

import hu.bme.fungi.Hyphae;
import java.util.Iterator;

public class AbsrobingTekton extends Tekton {
    
    private float absrobingRate;

    public AbsrobingTekton() {
        super();
    }

    public void absorbHyphae() {
        Iterator<Hyphae> iterator = fungalManager.getHyphaes().iterator();
        while (iterator.hasNext()) {
            Hyphae hyphae = iterator.next();
            System.out.println("[" + this + "] removeHyphae(" + hyphae + ") -> [" + this.fungalManager + "]");
            iterator.remove();
            removeHyphae(hyphae);
        }
        //  add javadoc
    }
}
