package hu.bme.fungi;

import hu.bme.tekton.Tekton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.spore.*;

public class Mycelium {
    
    private boolean upgraded;
    private List<Spore> spores;
    private List<Hyphae> hyphaes;
    private Tekton currentTekton;
    public int maxSporeRelease;

    public Mycelium() {
        upgraded = false;
        spores = new ArrayList<>();
        hyphaes = new ArrayList<>();
        currentTekton = null;

    }

    public boolean isUpgraded() { return upgraded; }

    public void upgrade() {
        upgraded = true;
    }

    public void growSpores(){
        // TODO implement function, add javadoc
    }

    public void releaseSpores() {
        maxSporeRelease--;
    }

    public void setCurrentTekton(Tekton tekton) {
        currentTekton = tekton;
    }

    public void addSpore(Spore spore) {
        spores.add(spore);
    }

    public void removeSpore(Spore spore) {
        spores.remove(spore);
    }
    

    public void addHyphae(Hyphae hyphae) {
        hyphaes.add(hyphae);
    }

 
    public void removeHyphae(Hyphae hyphae) {
        hyphaes.remove(hyphae);
    }
}
