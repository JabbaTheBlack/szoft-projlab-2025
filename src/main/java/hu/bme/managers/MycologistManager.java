package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.fungi.Mycologist;

public class MycologistManager {
    private static volatile MycologistManager instance;
    private List<Mycologist> mycologists;

    private MycologistManager() {
        mycologists = new ArrayList<>();
    }

    public MycologistManager getInstance() {
        MycologistManager result = instance;

        if(result == null) {
            synchronized(MycologistManager.class) {
                result = instance;
                if(result == null) {
                    instance = result = new MycologistManager();
                }
            }
        }
        return result;
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
