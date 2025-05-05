package hu.bme.fungi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import hu.bme.tekton.Tekton;
import hu.bme.view.TextureProvider;
import hu.bme.fungi.spore.*;

/**
 * Represents a hyphae in a fungal network, managing connections to other hyphae
 * and myceliums.
 */
public class Hyphae {
    private int timeToLive;
    private List<Hyphae> connectedHyphae;
    private List<Mycelium> connectedMyceliums;
    private List<Tekton> currentTekton;
    private Mycologist ownner;
    private boolean isOnKeeperTekton;
    public TextureProvider textureProvider;

    /**
     * Initializes a new hyphae with empty lists for connected hyphae and myceliums.
     */
    public Hyphae() {
        connectedHyphae = new ArrayList<>();
        connectedMyceliums = new ArrayList<>();
        currentTekton = new ArrayList<>();
        ownner = new Mycologist();
        timeToLive = -1;
        isOnKeeperTekton = false;
        textureProvider = new TextureProvider();
    }

    /**
     * Start of the round
     */
    public void tick() {

        if (isOnKeeperTekton) {
            return;
        }

        timeToLive--;
        if (timeToLive == 0) {

            if (currentTekton != null) {
                for (Tekton tekton : new ArrayList<>(currentTekton)) {
                    tekton.removeHyphae(this);
                }
            }
            currentTekton = null;

            if (getConnectedHyphae() != null) {
                for (Hyphae neighbour : new ArrayList<>(getConnectedHyphae())) {
                    neighbour.removeHyphae(this);
                }
            }

            if (getConnectedMyceliums() != null) {
                for (Mycelium neighbour : new ArrayList<>(getConnectedMyceliums())) {
                    neighbour.removeHyphae(this);
                }
            }

            connectedHyphae.clear();
            connectedMyceliums.clear();
            ownner.getHyphaes().remove(this);
        }
    }

    /**
     * Configures Keeper Tekton persistence status.
     * 
     * @param value true enables indefinite lifespan while on Tekton
     */
    public void setIsOnKeeperTekton(boolean value) {
        isOnKeeperTekton = value;
    }

    /**
     * Checks Keeper Tekton persistence status.
     * 
     * @return true if benefiting from Tekton-based lifespan extension
     */
    public boolean isOnKeeperTekton() {
        return isOnKeeperTekton;
    }

    /**
     * Sets the rounds left of the Hyphae's life
     * 
     * @param timeToLive Number of rounds the Hyphae will still lve
     */
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    /**
     * Gets the number of rounds left from the Hyphae's life
     * 
     * @return Number of rounds left from the Hyphae's life
     */
    public int getTimeToLive() {
        return timeToLive;
    }

    /**
     * Initializes a new hyphae with the specified Tekton.
     * 
     * @param currentTekton The Tekton to be associated with this hyphae.
     */
    public Hyphae(Tekton currenTekton) {
        this();
        this.currentTekton.add(currenTekton);
    }

    /**
     * Sets the given hyphae's owner.
     * 
     * @param owner
     */
    public void setOwner(Mycologist owner) {
        this.ownner = owner;
    }

    /**
     * Returns the owner of this hyphae.
     * 
     * @return The owner of this hyphae.
     */
    public Mycologist getOwner() {
        return ownner;
    }

    /**
     * Checks if this hyphae is connected to any mycelium.
     * 
     * @return true if connected to at least one mycelium, false otherwise.
     */
    public boolean isConnectedToMycelium() {

        if (!connectedMyceliums.isEmpty()) {
            return true;
        }

        Set<Hyphae> visited = new HashSet<>();
        Queue<Hyphae> queue = new LinkedList<>();

        queue.add(this);
        visited.add(this);

        while (!queue.isEmpty()) {
            Hyphae current = queue.poll();

            if (!current.connectedMyceliums.isEmpty()) {
                return true;
            }

            for (Hyphae neighbour : current.connectedHyphae) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        return false;
    }

    /**
     * Adds a mycelium to the list of connected myceliums.
     * 
     * @param mycelium The mycelium to be added.
     */
    public void addMycelium(Mycelium mycelium) {
        connectedMyceliums.add(mycelium);
    }

    /**
     * Removes a mycelium from the list of connected myceliums.
     * 
     * @param mycelium The mycelium to be removed.
     */
    public void removeMycelium(Mycelium mycelium) {
        connectedMyceliums.remove(mycelium);
    }

    /**
     * Adds a hyphae to the list of connected hyphae.
     * 
     * @param hyphae The hyphae to be added.
     */
    public void addHyphae(Hyphae hyphae) {
        if (!connectedHyphae.contains(hyphae)) {
            connectedHyphae.add(hyphae);
        }
    }

    /**
     * Removes a hyphae from the list of connected hyphae.
     * 
     * @param hyphae The hyphae to be removed.
     */
    public void removeHyphae(Hyphae hyphae) {
        connectedHyphae.remove(hyphae);
        hyphae.connectedHyphae.remove(this);

        if (!isConnectedToMycelium() && !isOnKeeperTekton) {
            setTimeToLive(2);
        } else if (isOnKeeperTekton) {
            setTimeToLive(-1);
        }
    }

    /**
     * Returns an unmodifiable list of connected hyphae.
     * 
     * @return List of connected hyphae.
     */
    public List<Hyphae> getConnectedHyphae() {
        return Collections.unmodifiableList(connectedHyphae);
    }

    /**
     * Returns an unmodifiable list of connected myceliums.
     * 
     * @return List of connected myceliums.
     */
    public List<Mycelium> getConnectedMyceliums() {
        return Collections.unmodifiableList(connectedMyceliums);
    }

    /**
     * Returns the current Tekton associated with this hyphae.
     * 
     * @return The current Tekton.
     */
    public List<Tekton> getCurrentTekton() {
        return currentTekton;
    }

    /**
     * Adds the hyphae to the list of current Tekton.
     * If hyphae is between two Tekton it will be added to both of them.
     * 
     * @param currentTekton The Tekton to be set.
     */
    public void addCurrentTekton(Tekton tekton) {
        if (!currentTekton.contains(tekton)) {
            currentTekton.add(tekton);
        }
    }

    /**
     * Removes the hyphae from the list of current Tekton.
     * If hyphae is between two Tekton it will be removed from both of them.
     * 
     * @param currentTekton The Tekton to be removed.
     */
    public void removeCurrentTekton(Tekton tekton) {
        currentTekton.remove(tekton);
    }
}
