package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Mycologist;

public class MycologistManager {
    
    private List<Mycologist> mycologists;

    public MycologistManager() {
        mycologists = new ArrayList<>();
    }

    public void addMycologist(Mycologist mycologist) {
        mycologists.add(mycologist);
    }

    public void removeMycologist(Mycologist mycologist) {
        mycologists.remove(mycologist);
    }

    public int getMycologistCount() {
        return mycologists.size();
    }
}
