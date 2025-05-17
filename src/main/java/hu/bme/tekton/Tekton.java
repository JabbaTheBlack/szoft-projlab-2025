package hu.bme.tekton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.Spore;
import hu.bme.managers.FungalManager;

/**
 * Represents a tekton, managing its connections to other tektons and
 * neighbours.
 */
public abstract class Tekton {

    protected final FungalManager fungalManager;
    private List<Tekton> neighbours;
    private List<Tekton> connectedNeighbours;
    private int x, y;

    // Setter for Y
    public void setY(int y) {
        this.y = y;
    }

    // Setter for X
    public void setX(int x) {
        this.x = x;
    }

    // Getter for Y
    public int getX() {
        return x;
    }

    // Getter for X
    public int getY() {
        return y;
    }

    /**
     * Initializes a new tekton with an empty list of neighbours and connected
     * neighbours.
     */
    protected Tekton() {
        fungalManager = new FungalManager();
        neighbours = new ArrayList<>();
        connectedNeighbours = new ArrayList<>();
        Random random = new Random();
        this.x = random.nextInt(800); // Példa: 0 és 800 közötti érték
        this.y = random.nextInt(600);
    }

    /**
     * Abstract function returning the instance of the tekton
     * 
     * @return Subclass of Tekton
     */
    public abstract Tekton createTekton();

    /**
     * Breaks the tekton apart, refreshing all connections of the tektons.
     */
    public List<Tekton> breakApart() {
        if (this.fungalManager.getMyceliumCount() == 0) {
            // Create new Tektons
            Tekton newTekton1 = createTekton();
            Tekton newTekton2 = createTekton();

            // Connect the new Tektons to each other
            newTekton1.addNeighbour(newTekton2);
            newTekton2.addNeighbour(newTekton1);

            // Remove this Tekton from the neighbor lists of its neighbors
            for (Tekton neighbor : new ArrayList<>(neighbours)) {
                neighbor.removeNeighbour(this); // Remove this Tekton from the neighbor's list
                neighbor.addNeighbour(newTekton1); // Add the new Tekton as a neighbor
                neighbor.addNeighbour(newTekton2); // Add the new Tekton as a neighbor
                newTekton1.addNeighbour(neighbor); // Add the neighbor to the new Tekton
                newTekton2.addNeighbour(neighbor); // Add the neighbor to the new Tekton
            }

            // Remove all Hyphaes
            for (Hyphae hyphae : new ArrayList<>(fungalManager.getHyphaes())) {
                hyphae.getConnectedHyphae().forEach(connectedHyphae -> connectedHyphae.removeHyphae(hyphae));
                fungalManager.removeHyphae(hyphae);
            }

            // Return the new Tektons
            List<Tekton> newTektons = new ArrayList<>();
            newTektons.add(newTekton1);
            newTektons.add(newTekton2);
            return newTektons;
        }
        return null;
    }

    /**
     * Connects the tekton to another tekton.
     * 
     * @param tekton The tekton to connect to.
     */
    public void connectToTekton(Tekton tekton) {

        if (!neighbours.contains(tekton)) {
            addNeighbour(tekton);
        }
        if (!tekton.neighbours.contains(this)) {
            tekton.addNeighbour(this);
        }

        if (!connectedNeighbours.contains(tekton)) {
            connectedNeighbours.add(tekton);
        }
        if (!tekton.getConnectedNeighbours().contains(this)) {
            tekton.getConnectedNeighbours().add(this);
        }
    }

    /**
     * Breaks the connection to another tekton.
     * 
     * @param tekton The tekton to disconnect from.
     */
    public void breakConnectionTo(Tekton tekton) {
        connectedNeighbours.remove(tekton);
    }

    /**
     * Refreshes the neighbours of all tektons.
     */
    public void refreshNeighbours() {

    }

    /**
     * Checks if the tekton is connected to another tekton.
     * 
     * @param tekton The tekton to check connection to.
     * @return True if the tekton is connected to the other tekton, false otherwise.
     */
    public boolean isConnectedTo(Tekton tekton) {
        return connectedNeighbours.contains(tekton);
    }

    /**
     * Adds a neighbour to the list of neighbours.
     * 
     * @param tekton The neighbour to add.
     */
    public void addNeighbour(Tekton tekton) {
        if (neighbours.contains(tekton) || tekton == this) {
            return;
        }
        neighbours.add(tekton);
        tekton.addNeighbour(this);
    }

    /**
     * Removes a neighbour from the list of neighbours.
     * 
     * @param tekton The neighbour to remove.
     */
    public void removeNeighbour(Tekton tekton) {
        neighbours.remove(tekton);
    }

    /**
     * Adds a mycelium to this tekton.
     * 
     * @param mycelium The mycelium to add.
     */
    public boolean addMycelium(Mycelium mycelium) {
        if (fungalManager.getMyceliumCount() == 0) {
            fungalManager.addMycelium(mycelium);
            return true;
        }
        System.out.println("[Mycoligist] <- addMycelium(" + mycelium + ") {false}");
        return false;
    }

    /**
     * Removes a mycelium from this tekton.
     * 
     * @param mycelium The mycelium to remove.
     */
    public void removeMycelium(Mycelium mycelium) {
        fungalManager.removeMycelium(mycelium);
    }

    /**
     * Adds a hyphae to this tekton.
     * 
     * @param hyphae The hyphae to add.
     */
    public boolean addHyphae(Hyphae hyphae) {
        if (hasHyphae(hyphae)) {
            return false;
        }
        fungalManager.addHyphae(hyphae);
        hyphae.addCurrentTekton(this);
        return true;
    }

    /**
     * Removes a hyphae from this tekton.
     * 
     * @param hyphae The hyphae to remove.
     */
    public void removeHyphae(Hyphae hyphae) {
        System.out.println("[" + this + "] removeHyphae(" + hyphae + ") -> [FungalManager]");
        fungalManager.removeHyphae(hyphae);
    }

    /**
     * Adds a spore to this tekton.
     * 
     * @param spore The spore to add.
     */
    public void addSpore(Spore spore) {
        fungalManager.addSpore(spore);
    }

    /**
     * Removes a spore from this tekton.
     * 
     * @param spore The spore to remove.
     */
    public void removeSpore(Spore spore) {
        System.out.println("[" + this + "] removeSpore(" + spore + ") -> [FungalManager]");
        fungalManager.removeSpore(spore);
    }

    /**
     * Returns the list of neighbours.
     * 
     * @return The list of neighbours.
     */
    public List<Tekton> getNeighbours() {
        return neighbours;
    }

    /**
     * Returns the list of connected neighbours.
     * 
     * @return The list of connected neighbours.
     */
    public List<Tekton> getConnectedNeighbours() {
        return connectedNeighbours;
    }

    /**
     * Checks if this tekton has a specific hyphae.
     * 
     * @param hyphae The hyphae to check for.
     * @return True if the hyphae is present, false otherwise.
     */
    public boolean hasHyphae(Hyphae hyphae) {
        return fungalManager.hasHyphae(hyphae);
    }

    /**
     * Returns the number of spores on this tekton.
     * 
     * @return The number of spores.
     */
    public int getSporeCount() {
        return fungalManager.getSporeCount();
    }

    /**
     * Returns a list of spores on this tekton.
     * 
     * @return The list of spores.
     */
    public List<Spore> getSpores() {
        return fungalManager.getSpores();
    }

    public List<Hyphae> getHyphaes() {
        return fungalManager.getHyphaes();
    }

    public void absorbHyphae() {
    }

    public boolean hasMycelium() {
        if (fungalManager.getMyceliumCount() > 0) {
            return true;
        }
        return false;

    }

/**
 * Ellenőrzi, hogy az aktuális Tekton-ból elérhető-e egy célpont Tekton adott mélységen belül.
 * Mélységkorlátos DFS algoritmust használ.
 * 
 * @param target A célpont Tekton, amelyet el szeretnénk érni
 * @param maxDepth A maximális mélység/lépésszám, ameddig keresünk
 * @return true, ha van út az aktuális Tekton-ból a célponthoz a megadott mélységen belül
 */
public boolean reachable(Tekton target, int maxDepth) {
    // Nyomon követjük a már meglátogatott csomópontokat
    Set<Tekton> visited = new HashSet<>();
    return dfsReachable(this, target, visited, 0, maxDepth);
}

/**
 * Rekurzív mélységkorlátos DFS algoritmus az elérhetőség ellenőrzésére
 * 
 * @param current Az aktuális Tekton
 * @param target A célpont Tekton
 * @param visited A már meglátogatott Tektonok halmaza
 * @param currentDepth Az aktuális mélység
 * @param maxDepth A maximális megengedett mélység
 * @return true, ha van út a current-ből a target-hez a megengedett mélységen belül
 */
private boolean dfsReachable(Tekton current, Tekton target, Set<Tekton> visited, 
                            int currentDepth, int maxDepth) {
    // Ha az aktuális csomópont a célpont, akkor elértük a célt
    if (current.equals(target)) {
        return true;
    }
    
    // Ha elértük a maximális mélységet, nem megyünk tovább
    if (currentDepth >= maxDepth) {
        return false;
    }
    
    // Megjelöljük az aktuális csomópontot meglátogatottként
    visited.add(current);
    
    // Rekurzívan bejárunk minden szomszédot, de csak a korlátozott mélységig
    for (Tekton neighbor : current.connectedNeighbours) {
        // Csak azokat a szomszédokat járjuk be, amelyeket még nem látogattunk meg
        if (!visited.contains(neighbor)) {
            // Növeljük a mélységet amikor a következő szintre lépünk
            if (dfsReachable(neighbor, target, visited, currentDepth + 1, maxDepth)) {
                return true;
            }
        }
    }
    
    // Ha ide jutottunk, nincs út a célponthoz a megengedett mélységen belül
    return false;
    }
}
