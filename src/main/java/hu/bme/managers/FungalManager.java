package hu.bme.managers;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.Spore;
import hu.bme.interfaces.IHyphaeManager;
import hu.bme.interfaces.IMyceliumManager;
import hu.bme.interfaces.ISporeManager;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("[" + this + "] removeHyphae("+ hyphae +") -> [" + hyphaeManager + "]");
        for (Hyphae rmv : hyphaeManager.getHyphaes()) {
            System.out.println("[" + hyphaeManager + "] removeHyphae("+ hyphae +") -> [" + rmv + "]");
            rmv.removeHyphae(hyphae);
        }
        //hyphaeManager.getHyphaes().forEach(h -> h.removeHyphae(hyphae));
        for (Mycelium rmv : myceliumManager.getMyceliums()) {
            System.out.println("[" + myceliumManager + "] removeHyphae("+ hyphae +") -> [" + rmv + "]");
            rmv.removeHyphae(hyphae);
            
        }
        hyphaeManager.removeHyphae(hyphae);

        //myceliumManager.getMyceliums().forEach(m -> m.removeHyphae(hyphae));
    }

    public void removeSpore(Spore spore) {
        sporeManager.removeSpore(spore);    
    }

    public int getMyceliumCount() {
        return myceliumManager.getMyceliumCount();
    }

    public int getHyphaeCount() {
        return hyphaeManager.getHyphaeCount();
    }

    public boolean hasHyphae(Hyphae hyphae) {
        return hyphaeManager.hasHyphae(hyphae);
    }

    public ArrayList<Hyphae> getHyphaes() {
        return hyphaeManager.getHyphaes();
    }
}
