package hu.bme.managers;

import hu.bme.interfaces.ISporeManager;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.spore.*;

public class SporeManager implements ISporeManager{
    private List<Spore> spores;

    public SporeManager() {
        spores = new ArrayList<>();
    }

    @Override
    public void addSpore(Spore spore) {
        spores.add(spore);
    }

    @Override
    public void removeSpore(Spore spore) {
        spores.remove(spore);
    }
}
