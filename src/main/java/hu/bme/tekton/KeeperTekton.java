package hu.bme.tekton;

import hu.bme.fungi.Hyphae;

public class KeeperTekton extends Tekton {
    
    public KeeperTekton(){
        super();
    }

    @Override
    public boolean addHyphae(Hyphae hyphae) {
        fungalManager.addHyphae(hyphae);
        hyphae.setIsOnKeeperTekton(true);
        return true;

    }

    @Override
    public Tekton createTekton() {
        return new KeeperTekton();
    }
}
