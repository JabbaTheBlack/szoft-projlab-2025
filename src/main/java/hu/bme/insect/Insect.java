package hu.bme.insect;

import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.Tekton;

public class Insect {
    private float nutrition;
    private Tekton currentTetkon;
    private float movementSpeed;
    private boolean canCutHyphae;

    public Insect(Tekton currentTekton, float movementSpeed) {
        this.currentTetkon = currentTekton;
        this.movementSpeed = movementSpeed;
    }

    public Insect() {
        this(null, 1);
        nutrition = 0;
        canCutHyphae = true;
    }

    public void setCurrentTekton(Tekton tekton) {
        currentTetkon = tekton;
    }

    public void move(Tekton targetTekton) {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'move' in Insect class");
    }

    public void eatSpore(Spore spore) {
        nutrition += spore.getNutrition(); 
        spore.applyEffect(this);
    }

    public void cutHyphae(Tekton targTekton) {
        // TODO implement function, add javadoc
        throw new UnsupportedOperationException("Unimplemented method 'cutHyphae' in Insect class");
    }

    public float getNutrition() {
        return nutrition;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setCutHyphae(boolean canCutHyphae) {
        this.canCutHyphae = canCutHyphae;
    }

    public boolean getCanCutHyphae() {
        return canCutHyphae;
    }   

    public Tekton getCurrentTekton() {
        return currentTetkon;
    }
}
