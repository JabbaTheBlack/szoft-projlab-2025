package hu.bme.fungi;

import hu.bme.interfaces.IHyphaeManager;
import hu.bme.interfaces.ISporeManager;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.spore.*;

public class Mycelium implements ISporeManager, IHyphaeManager{
    
    private boolean upgraded;
    private List<Spore> spores;
    private List<Hyphae> hyphaes;

    public Mycelium() {
        upgraded = false;
        spores = new ArrayList<>();
        hyphaes = new ArrayList<>();
    }

    public boolean isUpgraded() { return upgraded; }

    public void upgrade() {
        upgraded = true;
    }

    public void growSpores(){
        // TODO implement function, add javadoc
    }

    public void releaseSpores() {
        // TODO implement function, add javadoc
    }

    @Override
    public void addSpore(Spore spore) {
        spores.add(spore);
    }

    @Override
    public void removeSpore(Spore spore) {
        spores.remove(spore);
    }
    
    @Override
    public void addHyphae(Hyphae hyphae) {
        hyphaes.add(hyphae);
    }

    @Override 
    public void removeHyphae(Hyphae hyphae) {
        hyphaes.remove(hyphae);
    }
}
