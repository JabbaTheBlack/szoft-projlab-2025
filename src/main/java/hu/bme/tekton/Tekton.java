package hu.bme.tekton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.Spore;
import hu.bme.managers.FungalManager;

public abstract class Tekton {
    
    protected final FungalManager fungalManager;
    private List<Tekton> neighbours;
    private List<Tekton> connectedNeighbours;

    protected Tekton() {
        fungalManager = new FungalManager();
        neighbours = new ArrayList<>();
        connectedNeighbours = new ArrayList<>();
    }

    public void breakApart() {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'breakApart'");
    }

    public void connectToTekton(Tekton tekton) {

        if(!neighbours.contains(tekton)) {
            neighbours.add(tekton);
        }
        connectedNeighbours.add(tekton);
    }

    public void breakConnectionTo(Tekton tekton) {
        connectedNeighbours.remove(tekton);
    }

    public void refreshNeighbours(){
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'refreshNeighbours'");
    }

    public boolean isConnectedTo(Tekton tekton) {
        return connectedNeighbours.contains(tekton);
    }

    public void addNeighbour(Tekton tekton) {
        neighbours.add(tekton);
    }

    public void removeNeighbour(Tekton tekton) {
        neighbours.remove(tekton);
    }

    public void addMycelium(Mycelium mycelium) {
        if(fungalManager.getMyceliumCount() < 1) {
            fungalManager.addMycelium(mycelium);
        }        
    }

    public void removeMycelium(Mycelium mycelium)
    {
        fungalManager.removeMycelium(mycelium);
    }

    public void addHyphae(Hyphae hyphae) {
        fungalManager.addHyphae(hyphae);
    }

    public void removeHyphae(Hyphae hyphae) {
        fungalManager.removeHyphae(hyphae);
    }

    public void addSpore(Spore spore) {
        fungalManager.addSpore(spore);
    }

    public void removeSpore(Spore spore) {
        fungalManager.removeSpore(spore);
    }

    public List<Tekton> getNeighbours() {
        return neighbours;
    }

    public List<Tekton> getConnectedNeighbours() {
        return connectedNeighbours;
    }

    public boolean hasHyphae(Hyphae hyphae) {
        return fungalManager.hasHyphae(hyphae);
    }
}
