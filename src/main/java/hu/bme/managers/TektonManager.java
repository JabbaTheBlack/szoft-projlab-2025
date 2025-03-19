package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.tekton.Tekton;

public class TektonManager {
    
    private List<Tekton> tektons;

    public TektonManager() {
        tektons = new ArrayList<>();
    }
    
    public void addTekton(Tekton tekton) {
        tektons.add(tekton);
    }

    public void removeTekton(Tekton tekton) {
        tektons.remove(tekton);
    }

    public List<Tekton> getTektons() {
        return tektons;
    }
}
