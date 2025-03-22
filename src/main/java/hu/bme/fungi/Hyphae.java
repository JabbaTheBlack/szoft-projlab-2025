package hu.bme.fungi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.bme.tekton.Tekton;


public class Hyphae{
    private List<Hyphae> connectedHyphae;
    private List<Mycelium> connectedMyceliums;
    private Tekton currentTekton;
    private Mycologist ownner;

    public Hyphae() {
        connectedHyphae = new ArrayList<>();
        connectedMyceliums = new ArrayList<>();
        currentTekton = null;
        ownner = new Mycologist();
    }

    public void setOwner(Mycologist owner) {
        this.ownner = owner;
    }

    public Mycologist getOwner() {
        return ownner;
    }

    public boolean isConnectedToMycelium() {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'isConnectedToMycelium'");
    }

    public void addMycelium(Mycelium mycelium) {
        connectedMyceliums.add(mycelium);
    }

    public void removeMycelium(Mycelium mycelium) {
        connectedMyceliums.remove(mycelium);
    }

    public void addHyphae(Hyphae hyphae) {
        connectedHyphae.add(hyphae);
    }

    public void removeHyphae(Hyphae hyphae) {
        connectedHyphae.remove(hyphae);
    }

    public List<Hyphae> getConnectedHyphae() {
        return Collections.unmodifiableList(connectedHyphae);
    }

    public List<Mycelium> getConnectedMyceliums() {
        return Collections.unmodifiableList(connectedMyceliums);
    }

    public Tekton getCurrentTekton() {
        return currentTekton;
    } 

    public void setCurrentTekton(Tekton currentTekton) {
        this.currentTekton = currentTekton;
    }
}
