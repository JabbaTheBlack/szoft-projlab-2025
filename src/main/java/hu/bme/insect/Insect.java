package hu.bme.insect;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
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
        
        System.out.println("[Insect] isConenctedTo(" + targetTekton + ") -> [Tekton]");

        if(currentTekton.isConnectedTo(targetTekton)) {
            System.out.println("[Insect] isConnectedTo(" + targetTekton + ") <- [Tekton] {true}");
            System.out.println("[Insect] setCurrentTekton(" + this + ") -> [Insect]");
            this.setCurrentTekton(targetTekton);
            return;
        }
        
        System.out.println("[Insect] isConnectedTo(" + targetTekton + ") <- [Tekton] {false}");
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
                    break;
                }
            }

            for(Hyphae h : hyphae.getConnectedHyphae()) {
                System.out.println("[Insect] removeConnectedHyphae(" + h + ") -> [Hyphae]");
                h.removeHyphae(hyphae);
            }

            for(Mycelium mycelium : hyphae.getConnectedMyceliums()) {
                mycelium.removeHyphae(hyphae);
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
