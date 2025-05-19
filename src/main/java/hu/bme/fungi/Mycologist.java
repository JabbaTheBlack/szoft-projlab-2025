package hu.bme.fungi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import hu.bme.fungi.spore.CloneSpore;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.SlowingSpore;
import hu.bme.fungi.spore.SpeedBoostSpore;
import hu.bme.fungi.spore.Spore;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.insect.Insect;
import hu.bme.tekton.Tekton;

/**
 * Represents a mycologist who manages myceliums and interacts with Tekton.
 */
public class Mycologist {

    private ArrayList<Mycelium> myceliums;
    private List<Hyphae> hyphaes;
    private Spore selectedType;
    private Color color;
    private String name;
    private int score;

    /**
     * Initializes a new mycologist with an empty list of myceliums.
     */
    public Mycologist() {
        myceliums = new ArrayList<>();
        hyphaes = new ArrayList<>();
        score = 0;
    }

    /**
 * Constructs a new Mycologist instance with the specified name.
 * Initializes the mycologist's myceliums and hyphae lists as empty, and sets the initial score to zero.
 *
 * @param name the name of the Mycologist
 */
public Mycologist(String name) {
    this.name = name;
    myceliums = new ArrayList<>();
    hyphaes = new ArrayList<>();
    score = 0;
}

    /**
     * Retrieves the name of the Mycologist.
     *
     * @return the name of the Mycologist
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Mycologist.
     *
     * @param name the new name to set for the Mycologist
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the current score of the Mycologist.
     *
     * @return the score of the Mycologist
     */
    public int getScore() {
        return score;
    }

    /**
     * Releases spores from the specified mycelium and manages its lifecycle.
     * If the mycelium exhausts its spore releases, it is disconnected from Tekton
     * and its hyphae are removed.
     *
     * @param mycelium The mycelium to release spores from.
     */
    public void releaseSpore(Mycelium mycelium) {
        mycelium.releaseSpores();

        if (mycelium.getRemainingSporeReleases() == 0) {
            mycelium.setCurrentTekton(null);

            mycelium.removeAllHyphae();
        }
    }

    /**
     * Upgrades a mycelium.
     * 
     * @param mycelium the mycelium to be upgraded
     */
    public void upgradeMycelium(Mycelium mycelium) {
        if (myceliums.contains(mycelium)) {
            mycelium.upgrade();
        } else {
            System.out.println("[Mycologist] Failed to upgrade mycelium: Mycelium not found or not yours.");
        }
    }

    /**
     * Grows hyphae from a mycelium towards a Tekton.
     * 
     * @param hyphae       The hyphae to be grown.
     * @param targetTekton The Tekton to grow the hyphae to.
     */
    public void growHyphaeToTekton(Hyphae hyphae, Tekton targetTekton) {

        if (hyphae == null || targetTekton == null || !hyphaes.contains(hyphae) || !hyphae.isConnectedToMycelium() ||
            hyphae.getCurrentTekton().size() == 2) {
            return;
        }

        Tekton neighbourTekton = null;
        boolean isNeighbour = false;

        // Get tektons neighbouring the target
        for (Tekton tekton : hyphae.getCurrentTekton()) {
            if (tekton.getNeighbours().contains(targetTekton)) {
                isNeighbour = true;
                neighbourTekton = tekton;
                break;
            }
        }
        if (isNeighbour == false) {
            return;
        }

        // szomszédosak e a tektonok
        Hyphae newHyphae = new Hyphae();
        newHyphae.setOwner(hyphae.getOwner());

        // Adding tektons to the hyphae
        newHyphae.addCurrentTekton(targetTekton);
        newHyphae.addCurrentTekton(neighbourTekton);

        if (!targetTekton.addHyphae(newHyphae)) {
            System.out.println("[Mycologist] Failed to grow hyphae: Tekton rejected it.");
            newHyphae.removeCurrentTekton(neighbourTekton);
            newHyphae.removeCurrentTekton(targetTekton);
            return;
        }

        // Adding hyphae to the tekton
        neighbourTekton.addHyphae(newHyphae);

        newHyphae.addHyphae(hyphae);

        neighbourTekton.connectToTekton(targetTekton);

        targetTekton.connectToTekton(neighbourTekton);

        hyphae.addHyphae(newHyphae);

        hyphaes.add(newHyphae);

        // spore on the tekton, bc that it grows faster
        if (targetTekton.getSporeCount() >= 1) {
            Hyphae newHyphae2 = new Hyphae(targetTekton);

            if (targetTekton.addHyphae(newHyphae2)) {
                newHyphae.addHyphae(newHyphae2);
                newHyphae2.addHyphae(newHyphae);
                newHyphae2.setOwner(newHyphae.getOwner());
                hyphaes.add(newHyphae2);
                newHyphae2.addCurrentTekton(targetTekton);
            }
        }

    }

    public void growHyphaeOnTekton(Mycelium mycelium, Tekton targetTekton) {
        if (mycelium == null || targetTekton == null) {
            return;
        }

        Tekton currentTekton = mycelium.getCurrentTekton();
        if (currentTekton != targetTekton) {
            return;
        }

        Hyphae newHyphae = new Hyphae();
        targetTekton.addHyphae(newHyphae);
        mycelium.addHyphae(newHyphae);
        newHyphae.setOwner(this);
        newHyphae.addMycelium(mycelium);
        newHyphae.addCurrentTekton(targetTekton);
        hyphaes.add(newHyphae);

    }

    /**
     * Adds a mycelium to the list of managed myceliums.
     * 
     * @param mycelium The mycelium to be added.
     */
    public void addMycelium(Mycelium mycelium) {
        if (myceliums.contains(mycelium)) {
            return;
        }
        myceliums.add(mycelium);
        score++;
    }

    /**
     * Removes a mycelium from the list of managed myceliums, when a mycelium is
     * dies.
     * 
     * @param mycelium The mycelium to be removed.
     */
    public void removeMycelium(Mycelium mycelium) {
        myceliums.remove(mycelium);
    }

    /**
     * Grows mycelium from a hyphae on a given Tekton.
     * 
     * @param hyphae       The hyphae to be grown.
     * @param targetTekton The Tekton to grow the mycelium on.
     */
    public void growMycelium(Hyphae hyphae, Tekton targetTekton) {
        if (hyphae.getCurrentTekton().get(0) == targetTekton && hyphae.getCurrentTekton().size() == 1
                && hyphae.getCurrentTekton().get(0).getSporeCount() >= 3) {
            Mycelium newMycelium = new Mycelium(targetTekton);

            if (!targetTekton.addMycelium(newMycelium)) {
                return;
            }

            // remove spores
            for (int i = 0; i < 3; i++) {
                Spore spore = targetTekton.getSpores().get(0);
                targetTekton.removeSpore(spore);
            }

            addMycelium(newMycelium);

            newMycelium.addHyphae(hyphae);

            hyphae.addMycelium(newMycelium);
            newMycelium.textureProvider = myceliums.get(0).textureProvider;

            myceliums.add(newMycelium);

        } else {
            System.out.println(
                    "[Mycologist] Failed to grow mycelium: Hyphae is not on the target Tekton or there are not enough spores.");
        }
    }

    /**
     * Simulates the selection of a spore type by the user.
     * 
     * @param choice The choice of the user.
     */
    public void chooseSpore(int choice) {
        switch (choice) {
            case 1:
                selectedType = new StunSpore();
                break;
            case 2:
                selectedType = new DefensiveSpore();
                break;
            case 3:
                selectedType = new SpeedBoostSpore();
                break;
            case 4:
                selectedType = new SlowingSpore();
                break;
            case 5:
                selectedType = new CloneSpore();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    }

    /**
     * Grows a hyphae onto a Tetkton.
     * 
     * @param hyphae       The hyphae from which the new will grow from
     * @param targetTekton The tekton on which the new hyphae will grow on
     */
    public void growHyphaeOnTekton(Hyphae hyphae, Tekton targetTekton) {
        if (hyphae.getCurrentTekton().size() != 2 || !hyphae.getCurrentTekton().contains(targetTekton)
                || !hyphaes.contains(hyphae)) {
            return;
        }

        Hyphae newHyphae = new Hyphae();
        if (targetTekton.addHyphae(newHyphae)) {
            newHyphae.addCurrentTekton(targetTekton);
            newHyphae.addHyphae(hyphae);
            newHyphae.setOwner(hyphae.getOwner());
            hyphae.addHyphae(newHyphae);
            hyphaes.add(newHyphae);
        }
    }

    /**
     * Simulates the consumption of an insect by the fungal network. If conditions
     * are met,
     * adds a new Mycelium onto the insect's current Tekton and removes the insect
     * from its environment.
     *
     * @param insect The insect to be consumed by the fungal network.
     */
    public void eatInsect(Insect insect) {

        if (insect == null || !insect.isStunned()) {
            System.out.println("[Mycologist] Failed to consume insect: Insect is null or not stunned.");
            return;
        }

        HashSet<Tekton> tektons = new HashSet<>();
        for (Hyphae hyphae : hyphaes) {
            if (hyphae.getCurrentTekton().size() == 1) {
                tektons.add(hyphae.getCurrentTekton().get(0));
            }
        }

        if (tektons.contains(insect.getCurrentTekton())) {

            Tekton insectTekton = insect.getCurrentTekton();
            Mycelium newMycelium = myceliums.get(0).clone();
            insectTekton.addMycelium(newMycelium);
            newMycelium.setCurrentTekton(insectTekton);
            newMycelium.textureProvider = myceliums.get(0).textureProvider;
            this.myceliums.add(newMycelium);

            for (Hyphae hyphae : insectTekton.getHyphaes()) {
                if (hyphae.getCurrentTekton().size() == 1) {
                    hyphae.addMycelium(newMycelium);
                    newMycelium.addHyphae(hyphae);
                }
            }

            insect.getEntomologist().removeInsect(insect);
            insect.setCurrentTekton(null);
            System.out.println("[Mycologist] Insect consumed: " + insect);
        }
    }

    /**
     * Removes a specific hyphae from the fungal network managed by this Mycologist.
     *
     * @param hyphae The hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        hyphae.removeHyphae(hyphae);
        hyphaes.remove(hyphae);
    }

    /**
     * Executes lifecycle management for all managed hyphaes.
     */
    public void tick() {

        for (Hyphae hyphae : new ArrayList<>(hyphaes)) {
            hyphae.tick();
        }

        for (Hyphae hyphae : hyphaes) {
            if (hyphae != null && (!hyphae.isOnKeeperTekton() && !hyphae.isConnectedToMycelium())) {
                hyphae.setTimeToLive(1);
            }
        }

        for (Mycelium mycelium : myceliums) {
            growSpore(mycelium);
        }
    }

    /**
     * Retrieves all managed Myceliums in the fungal network.
     *
     * @return A list of all active Myceliums managed by this Mycologist.
     */
    public ArrayList<Mycelium> getMyceliums() {
        return myceliums;
    }

    public List<Hyphae> getHyphaes() {
        return hyphaes;
    }

    public Spore getSelectedType() {
        return selectedType;
    }

    public void growSpore(Mycelium mycelium) {
        Spore spore = selectedType.clone();
        mycelium.addSpore(spore);
    }

    public void addHyphae(Hyphae hyphae) {
        if (hyphaes.contains(hyphae)) {
            return;
        }
        hyphaes.add(hyphae);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
