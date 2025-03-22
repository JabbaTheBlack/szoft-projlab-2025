package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Mycelium;
import hu.bme.interfaces.IMyceliumManager;

public class MyceliumManager implements IMyceliumManager{
    private ArrayList<Mycelium> myceliums;

    public MyceliumManager() {
        myceliums = new ArrayList<>();
    }

    @Override
    public void addMycelium(Mycelium mycelium) {
        myceliums.add(mycelium);
    }

    @Override
    public void removeMycelium(Mycelium mycelium) {
        myceliums.remove(mycelium);
    }

    @Override
    public int getMyceliumCount() {
        return myceliums.size();
    }

    @Override
    public List<Mycelium> getMyceliums() {
        return myceliums;
    }
}
