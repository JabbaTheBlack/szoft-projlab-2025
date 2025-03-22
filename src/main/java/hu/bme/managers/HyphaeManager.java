package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.interfaces.IHyphaeManager;


public class HyphaeManager implements IHyphaeManager {
    
    private ArrayList<Hyphae> hyphaes;

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

    @Override
    public int getHyphaeCount() {
        return hyphaes.size();
    }

    @Override
    public boolean hasHyphae(Hyphae hyphae) {
        return hyphaes.contains(hyphae);
    }

    @Override
    public ArrayList<Hyphae> getHyphaes() {
        return hyphaes;
    }
}
