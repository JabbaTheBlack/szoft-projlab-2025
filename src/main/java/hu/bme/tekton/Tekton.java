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
    // public List<Tekton> breakApart() {
      
    //     if(this.fungalManager.getMyceliumCount() == 0){
                
    //         Tekton newTekton1 = createTekton();
    //         System.out.println("["+this+"] new() - -> ["+newTekton1+"]");
    //         Tekton newTekton2 = createTekton();
    //         System.out.println("["+this+"] new() - -> ["+newTekton2+"]");
    
    
    //         newTekton1.addNeighbour(newTekton2);
    //         newTekton2.addNeighbour(newTekton1);
    
    //         this.fungalManager.getHyphaes().forEach(hyphae -> {
    //             if(hyphae.getCurrentTekton().size() >= 2){
    //                 //hyphea a két tekton között
    //                 System.out.println("["+this+"] addHyphea("+hyphae+") -> ["+newTekton1+"]");
    //                 newTekton1.addHyphae(hyphae);
    //                 System.out.println("["+this+"] addCurrentTekton("+newTekton1+") -> ["+hyphae+"]");
    //                 hyphae.addCurrentTekton(newTekton1);
    //                 System.out.println("["+this+"] removeCurrentTekton("+this+") -> ["+hyphae+"]");
    //                 hyphae.removeCurrentTekton(this);
    //             } else{
    //                 //hyphea a tektonon belül
    //                 hyphae.getConnectedHyphae().forEach(nghHyphae -> {
    //                     System.out.println("["+this+"] removeHyphae("+hyphae+") -> ["+nghHyphae+"]");
    //                 nghHyphae.removeHyphae(hyphae);
    //                 });
    //             }
    //         });
            
    //         this.fungalManager.getSpores().forEach(spore -> {
    //             System.out.println("["+this+"] addSpore("+spore+") -> ["+newTekton1+"]");
    //             newTekton1.addSpore(spore);
                
    //         });
    
    //         List<Tekton> newTektons = new ArrayList<>();
    //         newTektons.add(newTekton1);
    //         newTektons.add(newTekton2);

    //         return newTektons;
    //     }
    //     return null;
    // }

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
            for(Hyphae hyphae : new ArrayList<>(fungalManager.getHyphaes())) {
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
     * @param tekton The tekton to connect to.
     */
    public void connectToTekton(Tekton tekton) {

        if(!neighbours.contains(tekton)) {
            addNeighbour(tekton);
        }
        if(!tekton.neighbours.contains(this)) {
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
     * @param tekton The tekton to disconnect from.
     */
    public void breakConnectionTo(Tekton tekton) {
        connectedNeighbours.remove(tekton);
    }

    /**
     * Refreshes the neighbours of all tektons.
     */
    public void refreshNeighbours(){
       
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
        if(neighbours.contains(tekton) || tekton == this) {
            return;
        }
        neighbours.add(tekton);
        tekton.addNeighbour(this);
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
        if(hasHyphae(hyphae)) {
            return false;
        }
        fungalManager.addHyphae(hyphae);
        hyphae.addCurrentTekton(this);
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

    public List<Hyphae> getHyphaes() {
        return fungalManager.getHyphaes();
    }
    public void absorbHyphae() {}
       
}
