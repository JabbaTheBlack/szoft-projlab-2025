package hu.bme.insect;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages insect populations within the ecosystem simulation, handling
 * lifecycle operations
 * and genetic replication through cloning.
 */
public class Entomologist {
    private List<Insect> insects;
    private String name;

    /**
     * Constructs an Entomologist with an empty insect population.
     */
    public Entomologist() {
        insects = new ArrayList<>();
    }

    public Entomologist(String name) {
        this.name = name;
        insects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds an insect to the managed population.
     * 
     * @param insect Insect to register in the ecosystem
     */
    public void addInsect(Insect insect) {
        insects.add(insect);
    }

    /**
     * Removes an insect from management.
     * 
     * @param insect Insect to deregister
     */
    public void removeInsect(Insect insect) {
        insects.remove(insect);
    }

    /**
     * Gets current population size.
     * 
     * @return Number of active insects under management
     */
    public int getInsectCount() {
        return insects.size();
    }

    public int getNutrition() {
        int nutrition = 0;
        for (Insect insect : insects) {
            nutrition += insect.getNutrition();
        }
        return nutrition;
    }

    /**
     * Advances all insects through one simulation round, triggering their lifecycle
     * updates.
     */
    public void tick() {
        for (Insect insect : insects) {
            insect.tick();
        }
    }

    /**
     * Creates genetic replica of an insect and adds it to the population.
     * Preserves the original's Tekton location.
     * 
     * @param insect Insect to clone
     */
    public void clone(Insect insect) {
        Insect newInsect = new Insect();
        newInsect.setEntomologist(this);
        newInsect.setCurrentTekton(insect.getCurrentTekton());
        newInsect.textureProvider = insect.textureProvider;
        insects.add(newInsect);
    }

    public List<Insect> getInsects() {
        return insects;
    }
}
