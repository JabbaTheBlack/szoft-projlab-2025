package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.interfaces.IHyphaeManager;


public class HyphaeManager implements IHyphaeManager {
    private List<Hyphae> hyphaes;

    public HyphaeManager() {
        hyphaes = new ArrayList<>();
    }

    @Override
    public void addHyphae(Hyphae hyphae) {
        hyphaes.add(hyphae);
    }

    @Override
    public void removeHyphae(Hyphae hyphae) {
        hyphaes.remove(hyphae);
    }    

    public int getHyphaeCount() {
        return hyphaes.size();
    }
}
