package hu.bme.tekton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.Spore;
import hu.bme.managers.FungalManager;

/**
 * Represents a tekton, managing its connections to other tektons and neighbours.
 */
public abstract class Tekton {
    
    protected final FungalManager fungalManager;
    private List<Tekton> neighbours;
    private List<Tekton> connectedNeighbours;

    /**
     * Initializes a new tekton with an empty list of neighbours and connected neighbours.
     */
    protected Tekton() {
        fungalManager = new FungalManager();
        neighbours = new ArrayList<>();
        connectedNeighbours = new ArrayList<>();
    }

    /**
     * Abstract function returning the instance of the tekton
     * @return Subclass of Tekton
     */
    public abstract Tekton createTekton();

    /**
     * Breaks the tekton apart, refreshing all connections of the tektons.
     */
    public List<Tekton> breakApart() {
      
        if(this.fungalManager.getMyceliumCount() == 0){
                
            Tekton newTekton1 = createTekton();
            System.out.println("["+this+"] new() - -> ["+newTekton1+"]");
            Tekton newTekton2 = createTekton();
            System.out.println("["+this+"] new() - -> ["+newTekton2+"]");
    
    
            newTekton1.addNeighbour(newTekton2);
            newTekton2.addNeighbour(newTekton1);
    
            this.fungalManager.getHyphaes().forEach(hyphae -> {
                if(hyphae.getCurrentTekton().size() >= 2){
                    //hyphea a két tekton között
                    System.out.println("["+this+"] addHyphea("+hyphae+") -> ["+newTekton1+"]");
                    newTekton1.addHyphae(hyphae);
                    System.out.println("["+this+"] addCurrentTekton("+newTekton1+") -> ["+hyphae+"]");
                    hyphae.addCurrentTekton(newTekton1);
                    System.out.println("["+this+"] removeCurrentTekton("+this+") -> ["+hyphae+"]");
                    hyphae.removeCurrentTekton(this);
                } else{
                    //hyphea a tektonon belül
                    hyphae.getConnectedHyphae().forEach(nghHyphae -> {
                        System.out.println("["+this+"] removeHyphae("+hyphae+") -> ["+nghHyphae+"]");
                    nghHyphae.removeHyphae(hyphae);
                    });
                }
            });
            
            this.fungalManager.getSpores().forEach(spore -> {
                System.out.println("["+this+"] addSpore("+spore+") -> ["+newTekton1+"]");
                newTekton1.addSpore(spore);
                
            });
    
            List<Tekton> newTektons = new ArrayList<>();
            newTektons.add(newTekton1);
            newTektons.add(newTekton2);

            return newTektons;
        }
        return null;
    }

    /**
     * Connects the tekton to another tekton.
     * @param tekton The tekton to connect to.
     */
    public void connectToTekton(Tekton tekton) {

        if(!neighbours.contains(tekton)) {
            neighbours.add(tekton);
        }
        connectedNeighbours.add(tekton);
    }

    /**
     * Breaks the connection to another tekton.
     * @param tekton The tekton to disconnect from.
     */
    public void breakConnectionTo(Tekton tekton) {
        connectedNeighbours.remove(tekton);
    }

    /**
     * Refreshes the neighbours of all tektons.
     */
    public void refreshNeighbours(){
        // TODO implement function, add javadoc
        
    }

    /**
     * Checks if the tekton is connected to another tekton.
     * @param tekton The tekton to check connection to.
     * @return True if the tekton is connected to the other tekton, false otherwise.
     */
    public boolean isConnectedTo(Tekton tekton) {
        return connectedNeighbours.contains(tekton);
    }

    /**
     * Adds a neighbour to the list of neighbours.
     * @param tekton The neighbour to add.
     */
    public void addNeighbour(Tekton tekton) {
        neighbours.add(tekton);
    }

    /**
     * Removes a neighbour from the list of neighbours.
     * @param tekton The neighbour to remove.
     */
    public void removeNeighbour(Tekton tekton) {
        neighbours.remove(tekton);
    }

    /**
     * Adds a mycelium to this tekton.
     * @param mycelium The mycelium to add.
     */
    public boolean addMycelium(Mycelium mycelium) {
        if(fungalManager.getMyceliumCount() == 0) {
            fungalManager.addMycelium(mycelium);
            return true;
        }
        System.out.println("[Mycoligist] <- addMycelium("+mycelium+") {false}");
        return false;
    }

    /**
     * Removes a mycelium from this tekton.
     * @param mycelium The mycelium to remove.
     */
    public void removeMycelium(Mycelium mycelium)
    {
        fungalManager.removeMycelium(mycelium);
    }

  
    /**
     * Adds a hyphae to this tekton.
     * @param hyphae The hyphae to add.
     */
    public boolean addHyphae(Hyphae hyphae) {
        fungalManager.addHyphae(hyphae);
        return true;
    }

    /**
     * Removes a hyphae from this tekton.
     * @param hyphae The hyphae to remove.
     */
    public void removeHyphae(Hyphae hyphae) {
        System.out.println("["+this+"] removeHyphae("+hyphae+") -> [FungalManager]");
        fungalManager.removeHyphae(hyphae);
    }

    /**
     * Adds a spore to this tekton.
     * @param spore The spore to add.
     */
    public void addSpore(Spore spore) {
        fungalManager.addSpore(spore);
    }

    /**
     * Removes a spore from this tekton.
     * @param spore The spore to remove.
     */
    public void removeSpore(Spore spore) {
        System.out.println("["+this+"] removeSpore("+spore+") -> [FungalManager]");
        fungalManager.removeSpore(spore);
    }

    /**
     * Returns the list of neighbours.
     * @return The list of neighbours.
     */
    public List<Tekton> getNeighbours() {
        return neighbours;
    }

    /**
     * Returns the list of connected neighbours.
     * @return The list of connected neighbours.
     */
    public List<Tekton> getConnectedNeighbours() {
        return connectedNeighbours;
    }

    /**
     * Checks if this tekton has a specific hyphae.
     * @param hyphae The hyphae to check for.
     * @return True if the hyphae is present, false otherwise.
     */
    public boolean hasHyphae(Hyphae hyphae) {
        return fungalManager.hasHyphae(hyphae);
    }

    /**
     * Returns the number of spores on this tekton.
     * @return The number of spores.
     */
    public int getSporeCount() {
        return fungalManager.getSporeCount();
    }

    /**
     * Returns a list of spores on this tekton.
     * @return The list of spores.
     */
    public List<Spore> getSpores(){
        return fungalManager.getSpores();
    }
}
