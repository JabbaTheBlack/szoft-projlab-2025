package hu.bme.tekton;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycologist;

public class SingleTypeTekton extends Tekton {
    
    private Mycologist owner;

    public SingleTypeTekton() {
        super();
        owner = null;
    }

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
}
