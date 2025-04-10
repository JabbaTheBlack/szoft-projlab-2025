package hu.bme.fungi.mycelium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.Tekton;

public class DefensiveMycelium extends Mycelium<DefensiveSpore> {
    public DefensiveMycelium(){
        super();
        spores = new ArrayList<>();
    }

    public DefensiveMycelium(Tekton currentTekton) {
        super(currentTekton);
    }

    @Override
    public Mycelium<DefensiveSpore> cloneMycelium(Tekton targetTekton) {
        return new DefensiveMycelium(targetTekton);
    }

    @Override
    public Mycelium<DefensiveSpore> cloneMycelium() {
        return new DefensiveMycelium();
    }
}
