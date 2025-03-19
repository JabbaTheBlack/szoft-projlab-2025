package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.insect.Insect;

public class InsectManager {
    
    private List<Insect> insects;
    
    public InsectManager() {
        insects = new ArrayList<>();
    }

    public void addInsect(Insect insect) {
        insects.add(insect);
    }

    public void removeInsect(Insect insect) {
        insects.remove(insect);
    }

    public int getInsectCount() {
        return insects.size();
    }
}
