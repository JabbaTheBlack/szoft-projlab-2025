package hu.bme.fungi;

import hu.bme.tekton.Tekton;
import hu.bme.view.TextureProvider;

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
    public TextureProvider textureProvider;

    /**
     * Initializes a new mycelium with empty lists for spores, hyphae, unupgraded.
     */
    public Mycelium() {
        upgraded = false;
        spores = new ArrayList<>();
        hyphaes = new ArrayList<>();
        currentTekton = null;
        maxSporeRelease = 10;
        textureProvider = new TextureProvider();
    }

    /**
     * Initializes a new mycelium with the specified Tekton.
     * 
     * @param currentTekton The Tekton to be associated with this mycelium.
     */
    public Mycelium(Tekton currentTekton) {
        this();
        this.currentTekton = currentTekton;
    }

    /**
     * Creates a deep copy without spore/hyphae connections.
     * 
     * @return New Mycelium instance with default configuration
     */
    public Mycelium clone() {
        return new Mycelium();
    }

    /**
     * Returns the number of possible spore releases, before the mycelium dies.
     * 
     * @return The number of possible spore releases.
     */
    public int getRemainingSporeReleases() {
        return maxSporeRelease;
    }

    /**
     * Checks if the mycelium is upgraded.
     * 
     * @return true if the mycelium is upgraded, false otherwise.
     */
    public boolean isUpgraded() {
        return upgraded;
    }

    /**
     * Upgrades the mycelium.
     */
    public void upgrade() {
        if (currentTekton.getSporeCount() >= 3) {
            upgraded = true;

            List<Spore> tektonspores = currentTekton.getSpores();
            Random random = new Random();

            for (int i = 0; i < 3; i++) {
                if (!tektonspores.isEmpty()) {
                    Spore randomSpore = tektonspores.get(random.nextInt(tektonspores.size()));
                    currentTekton.removeSpore(randomSpore);
                }
            }
            System.out.println("Upgraded");
        } else {
            System.out.println("Not Upgraded");
        }
    }

    /**
     * Adds more spores to the mycelium.
     */
    public void growSpores(Spore spore) {
        this.spores.add(spore);
    }

    /**
     * Releases spores from the mycelium.
     */
    public void releaseSpores() {
        if (spores.isEmpty()) {
            return;
        }

        Random random = new Random();
        List<Tekton> targets = new ArrayList<>(currentTekton.getNeighbours());

        if (upgraded) {
            Set<Tekton> allNeighbours = new HashSet<>(targets);
            for (Tekton neighbour : targets) {
                allNeighbours.addAll(neighbour.getNeighbours());
            }
            allNeighbours.remove(currentTekton);
            targets = new ArrayList<>(allNeighbours);
        }

        Collections.shuffle(targets, random);

        while (!spores.isEmpty() && !targets.isEmpty()) {
            Tekton randomTekton = targets.remove(random.nextInt(targets.size()));

            randomTekton.addSpore(spores.get(0));

            removeSpore(spores.get(0));
            maxSporeRelease--;

            if (maxSporeRelease == 0) {
                currentTekton.removeMycelium(this);
                return;
            }
        }
    }

    /**
     * Sets the given mycelium's current Tekton.
     * 
     * @param tekton The Tekton to be associated with this mycelium.
     */
    public void setCurrentTekton(Tekton tekton) {
        currentTekton = tekton;
    }

    public Tekton getCurrentTekton() {
        return currentTekton;
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
     * 
     * @return the hyphae to be added.
     */
    public void addHyphae(Hyphae hyphae) {
        hyphaes.add(hyphae);
    }

    /**
     * Removes a hyphae from the mycelium.
     * 
     * @return the hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        hyphaes.remove(hyphae);
    }

    /**
     * Removes all hyphae from the mycelium.
     */
    public void removeAllHyphae() {
        for (Hyphae hyphae : hyphaes) {
            hyphae.removeMycelium(this);
        }
    }

    public int getSporeCount() {
        return spores.size();
    }
}
