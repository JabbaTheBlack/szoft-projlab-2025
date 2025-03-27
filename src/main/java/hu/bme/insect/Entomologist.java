package hu.bme.insect;

import java.util.ArrayList;
import java.util.List;

public class Entomologist {
    private List<Insect> insects;

    public Entomologist() {
        insects = new ArrayList<>();
    }

    public void addInsect(Insect insect){
        insects.add(insect);
    }

    public void removeInsect(Insect insect) {
        insects.remove(insect);
    }

    public int getInsectCount() {
        return insects.size();
    }
}
