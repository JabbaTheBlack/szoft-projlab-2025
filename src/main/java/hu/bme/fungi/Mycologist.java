package hu.bme.fungi;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.spore.SporeEffect;
import hu.bme.interfaces.IMyceliumManager;
import hu.bme.tekton.Tekton;

public class Mycologist implements IMyceliumManager{

    private SporeEffect selectedSporeEffect;
    private List<Mycelium> myceliums;

    public Mycologist() {
        myceliums = new ArrayList<>();
    }

    public void chooseSporeEffect(SporeEffect sporeEffect) {
        selectedSporeEffect = sporeEffect;
    }

    public void releaseSpore(Mycelium mycelium) {   
        mycelium.releaseSpores();
    }

    public void upgradeMyceium(Mycelium mycelium) {
        mycelium.upgrade();
    }

    public void growHyphaeToTekton(Tekton tekton) {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'growHyphaeToTekton' in Mycologist class");
    }

    @Override
    public void addMycelium(Mycelium mycelium) {
        myceliums.add(mycelium);
    }

    @Override
    public void removeMycelium(Mycelium mycelium) {
        myceliums.remove(mycelium);    
    }
    
}
