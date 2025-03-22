package hu.bme.fungi;

import java.util.ArrayList;
import java.util.List;

import hu.bme.tekton.Tekton;

public class Mycologist {

    private List<Mycelium> myceliums;

    public Mycologist() {
        myceliums = new ArrayList<>();
    }

    public void releaseSpore(Mycelium mycelium) {   
        mycelium.releaseSpores();
    }

    public void upgradeMyceium(Mycelium mycelium) {
        mycelium.upgrade();
    }

    public void growHyphaeToTekton(Hyphae hyphae, Tekton targetTekton) {
        // TODO add javadoc
        
        System.out.println("[Mycologist] new Hyphae() -> [Mycologist]");
        Hyphae newHyphae = new Hyphae();
        newHyphae.setOwner(hyphae.getOwner()); // Ensure owner consistency

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Tekton]");
        if (!targetTekton.addHyphae(newHyphae)) {
            System.out.println("[Mycologist] Failed to grow hyphae: Tekton rejected it.");
            return;  // Stop execution if adding hyphae fails
        }

        System.out.println("[Mycologist] setCurrentTekton(" + targetTekton + ") -> [Hyphae]");
        newHyphae.setCurrentTekton(targetTekton);

        System.out.println("[Mycologist] connectToTekton(" + targetTekton + ") -> [Tekton]");
        hyphae.getCurrentTekton().connectToTekton(targetTekton);

        System.out.println("[Mycologist] connectToTekton(" + hyphae.getCurrentTekton() + ") -> [Tekton]");
        targetTekton.connectToTekton(hyphae.getCurrentTekton());

        System.out.println("[Mycologist] addHyphae(" + newHyphae + ") -> [Hyphae]");
        hyphae.addHyphae(newHyphae);

    }

    public void addMycelium(Mycelium mycelium) {
        myceliums.add(mycelium);
    }

    public void removeMycelium(Mycelium mycelium) {
        myceliums.remove(mycelium);    
    }
    
}
