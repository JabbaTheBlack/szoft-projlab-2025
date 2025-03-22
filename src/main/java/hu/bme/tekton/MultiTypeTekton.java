package hu.bme.tekton;

import hu.bme.fungi.Hyphae;

public class MultiTypeTekton extends Tekton {
    
    public MultiTypeTekton() {
        super();
    }

    @Override
    public boolean addHyphae(Hyphae hyphae) {
        if(!hasHyphae(hyphae)) {
            fungalManager.addHyphae(hyphae);
            return true;
        }
        return false;   
    }
}
