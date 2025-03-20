package hu.bme.fungi;

import java.util.ArrayList;
import java.util.List;

public class Hyphae{
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
}
