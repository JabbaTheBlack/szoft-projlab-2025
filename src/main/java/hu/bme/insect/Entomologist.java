package hu.bme.insect;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages insect populations within the ecosystem simulation, handling lifecycle operations
 * and genetic replication through cloning.
 */
public class Entomologist {
    private List<Insect> insects;

    /**
     * Constructs an Entomologist with an empty insect population.
     */
    public Entomologist() {
        insects = new ArrayList<>();
    }

    /**
     * Adds an insect to the managed population.
     * @param insect Insect to register in the ecosystem
     */
    public void addInsect(Insect insect){
        insects.add(insect);
    }

    /**
     * Removes an insect from management.
     * @param insect Insect to deregister
     */
    public void removeInsect(Insect insect) {
        insects.remove(insect);
    }

    /**
     * Gets current population size.
     * @return Number of active insects under management
     */
    public int getInsectCount() {
        return insects.size();
    }

    /**
     * Advances all insects through one simulation round, triggering their lifecycle updates.
     */
    public void tick() {
        for(Insect insect : insects) {
            insect.tick();
        }
    }

    /**
     * Creates genetic replica of an insect and adds it to the population.
     * Preserves the original's Tekton location.
     * @param insect Insect to clone
     */
    public void clone(Insect insect){
        Insect newInsect = new Insect();
        newInsect.setCurrentTekton(insect.getCurrentTekton());
        insects.add(newInsect);
    }
}
