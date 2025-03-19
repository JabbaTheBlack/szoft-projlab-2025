package hu.bme.managers;

import java.util.ArrayList;
import java.util.List;

import hu.bme.insect.Insect;

public class InsectManager {
    
    private static volatile InsectManager instance;
    private List<Insect> insects;
    
    private InsectManager() {
        insects = new ArrayList<>();
    }

    public static InsectManager getInstance() {
        InsectManager result = instance;

        if(result == null) {
            synchronized(InsectManager.class) {
                result = instance;
                if(result == null) {
                    instance = result = new InsectManager();
                }
            }
        }
        return result;
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
