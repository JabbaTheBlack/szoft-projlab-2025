package hu.bme.tekton;

import hu.bme.fungi.Hyphae;

public class MultiTypeTekton extends Tekton {
    
    public MultiTypeTekton() {
        super();
    }

    @Override
    public void addHyphae(Hyphae hyphae) {
        fungalManager.addHyphae(hyphae);
    }
    
}
