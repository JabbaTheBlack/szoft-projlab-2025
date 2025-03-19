package hu.bme.managers;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.Spore;
import hu.bme.interfaces.IHyphaeManager;
import hu.bme.interfaces.IMyceliumManager;
import hu.bme.interfaces.ISporeManager;

public class FungalManager {
    private IMyceliumManager myceliumManager;
    private IHyphaeManager hyphaeManager;
    private ISporeManager sporeManager;

    public FungalManager() {
        myceliumManager = new MyceliumManager();
        hyphaeManager = new HyphaeManager();
        sporeManager = new SporeManager();
    }

    public void addMycelium(Mycelium mycelium) {
        myceliumManager.addMycelium(mycelium);
    }

    public void addHyphae(Hyphae hyphae) {
        hyphaeManager.addHyphae(hyphae);
    }

    public void addSpore(Spore spore) {
        sporeManager.addSpore(spore);
    }

    public void removeMycelium(Mycelium mycelium) {
        myceliumManager.removeMycelium(mycelium);
    }

    public void removeHyphae(Hyphae hyphae) {
        hyphaeManager.removeHyphae(hyphae);
    }

    public void removeSpore(Spore spore) {
        sporeManager.removeSpore(spore);    
    }
}
