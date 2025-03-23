package hu.bme.fungi;

import hu.bme.tekton.Tekton;

import java.util.*;

import hu.bme.fungi.spore.*;

/**
 * Represents a mycelium in a fungal network, managing spores and hyphae.
 */
public class Mycelium {
    
    private boolean upgraded;
    private List<Spore> spores;
    private List<Hyphae> hyphaes;
    private Tekton currentTekton;
    private int maxSporeRelease;

    /**
     * Initializes a new mycelium with empty lists for spores, hyphae, unupgraded.
     */
    public Mycelium() {
        upgraded = false;
        spores = new ArrayList<>();
        hyphaes = new ArrayList<>();
        currentTekton = null;
        maxSporeRelease = 1;
    }

    /**
     * Initializes a new mycelium with the specified Tekton.
     * @param currentTekton The Tekton to be associated with this mycelium.
     */
    public Mycelium(Tekton currentTekton) {
        this();
        this.currentTekton = currentTekton;
    }

    /**
     * Returns the number of possible spore releases, before the mycelium dies.
     * @return The number of possible spore releases.
     */
    public int getRemainingSporeReleases(){
        return maxSporeRelease;
    }

    /**
     * Checks if the mycelium is upgraded.
     * @return true if the mycelium is upgraded, false otherwise.
     */
    public boolean isUpgraded() { return upgraded; }

    /**
     * Upgrades the mycelium.
     */
    public void upgrade() {
        upgraded = true;
    }

    /**
     * Adds more spores to the mycelium.
     */
    public void growSpores(){
        // TODO implement function
    }

    /**
     * Releases spores from the mycelium.
     */
    public void releaseSpores() {
        if(spores.isEmpty()) { return; }
        
        Random random = new Random();
        List<Tekton> targets = new ArrayList<>(currentTekton.getNeighbours());

        if(upgraded) {
            Set<Tekton> allNeighbours = new HashSet<>(targets);
            for(Tekton neighbour : targets) {
                allNeighbours.addAll(neighbour.getNeighbours());
            }
            allNeighbours.remove(currentTekton);
            targets = new ArrayList<>(allNeighbours);
        }

        Collections.shuffle(targets, random);

        while(!spores.isEmpty() && !targets.isEmpty()) {
            Tekton randomTekton = targets.remove(random.nextInt(targets.size()));

            System.out.println("[Mycelium] addSpore(" + spores.get(0) + ") -> [" + randomTekton + "]");
                randomTekton.addSpore(spores.get(0));

            System.out.println("[Mycelium] removeSpore(" + spores.get(0) + ") -> [" + this + "]");
            removeSpore(spores.get(0));
            maxSporeRelease--;

            if(maxSporeRelease == 0){
                currentTekton.removeMycelium(this);
                return;
            }
        }
    }

    /**
     * Sets the given mycelium's current Tekton.
     * @param tekton The Tekton to be associated with this mycelium.
     */
    public void setCurrentTekton(Tekton tekton) {
        currentTekton = tekton;
    }

    /**
     * Increment the spore count of the mycelium by one.
     */
    public void addSpore(Spore spore) {
        spores.add(spore);
    }

    /**
     * Decrement the spore count of the mycelium by one.
     */
    public void removeSpore(Spore spore) {
        spores.remove(spore);
    }
    
    /**
     * Adds a hyphae to the mycelium.
     * @return the hyphae to be added.
     */
    public void addHyphae(Hyphae hyphae) {
        hyphaes.add(hyphae);
    }

    /**
     * Removes a hyphae from the mycelium.
     * @return the hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        hyphaes.remove(hyphae);
    }

    /**
     * Removes all hyphae from the mycelium.
     */
    public void removeAllHyphae(){
        for(Hyphae hyphae : hyphaes) {
            hyphae.removeMycelium(this);
        }
    }
}
