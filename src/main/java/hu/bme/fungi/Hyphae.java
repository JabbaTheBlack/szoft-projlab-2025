package hu.bme.fungi;

import java.util.ArrayList;
import java.util.List;

import hu.bme.interfaces.IHyphaeManager;
import hu.bme.interfaces.IMyceliumManager;

public class Hyphae implements IMyceliumManager, IHyphaeManager{
    private List<Hyphae> connectedHyphae;
    private List<Mycelium> connectedMyceliums;

    public Hyphae() {
        connectedHyphae = new ArrayList<>();
        connectedMyceliums = new ArrayList<>();
    }

    public boolean isConnectedToMycelium() {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'isConnectedToMycelium'");
    }

    @Override
    public void addMycelium(Mycelium mycelium) {
        connectedMyceliums.add(mycelium);
    }

    @Override
    public void removeMycelium(Mycelium mycelium) {
        connectedMyceliums.remove(mycelium);
    }

    @Override
    public void addHyphae(Hyphae hyphae) {
        connectedHyphae.add(hyphae);
    }

    @Override
    public void removeHyphae(Hyphae hyphae) {
        connectedHyphae.remove(hyphae);
    }  
}
