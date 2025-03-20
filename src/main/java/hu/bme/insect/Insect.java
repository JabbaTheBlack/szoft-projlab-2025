package hu.bme.insect;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.Tekton;

public class Insect {
    private float nutrition;
    private Tekton currentTekton;
    private float movementSpeed;
    private boolean canCutHyphae;

    public Insect(Tekton currentTekton, float movementSpeed) {
        this.currentTekton = currentTekton;
        this.movementSpeed = movementSpeed;
    }

    public Insect() {
        this(null, 1);
        nutrition = 0;
        canCutHyphae = true;
    }

    public void setCurrentTekton(Tekton tekton) {
        currentTekton = tekton;
    }

    public void move(Tekton targetTekton) {
        // TODO implement function, add javadoc
        if(currentTekton.isConnectedTo(targetTekton)) {
            currentTekton = targetTekton;
        }
    }

    public void eatSpore(Spore spore) {
        nutrition += spore.getNutrition(); 
        spore.applyEffect(this);
    }

    public void cutHyphae(Hyphae hyphae) {
        // TODO add javadoc
        if(canCutHyphae) {
            for(Tekton tekton : currentTekton.getConnectedNeighbours()) {
                if(tekton.hasHyphae(hyphae)) {
                    tekton.removeHyphae(hyphae);
                    tekton.breakConnectionTo(currentTekton);
                    currentTekton.breakConnectionTo(tekton);
                }
            }
            currentTekton.removeHyphae(hyphae);
        }
    }

    public float getNutrition() {
        return nutrition;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setCanCutHyphae(boolean canCutHyphae) {
        this.canCutHyphae = canCutHyphae;
    }

    public boolean getCanCutHyphae() {
        return canCutHyphae;
    }   

    public Tekton getCurrentTekton() {
        return currentTekton;
    }
}
