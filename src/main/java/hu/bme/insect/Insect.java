package hu.bme.insect;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.Tekton;

/**
 * Represents an insect that can move between Tektons on hyphae and interact
 * with fungi.
 */
public class Insect {
    private int effectDuration;
    private float nutrition;
    private Tekton currentTekton;
    private float movementSpeed;
    private boolean canCutHyphae;
    private Entomologist owner;

    /**
     * Initializes an insect at a specified Tekton with a given movement speed.
     * 
     * @param currentTekton The initial Tekton where the insect is located.
     * @param movementSpeed The speed at which the insect moves.
     */
    public Insect(Tekton currentTekton, float movementSpeed) {
        this.currentTekton = currentTekton;
        this.movementSpeed = movementSpeed;
        effectDuration = -1;
        owner = null;
    }

    /**
     * Default constructor for an insect. Initializes it at a null Tekton with
     * default movement speed and nutrition.
     */
    public Insect() {
        this(null, 1);
        nutrition = 0;
        canCutHyphae = true;
    }

    /**
     * Associates with an entomologist for population tracking.
     * 
     * @param owner Entomologist managing this insect
     */
    public void setEntomologist(Entomologist owner) {
        this.owner = owner;
    }

    /**
     * Gets managing entomologist.
     * 
     * @return Associated population controller
     */
    public Entomologist getEntomologist() {
        return owner;
    }

    /**
     * Updates insect state each simulation round.
     */
    public void tick() {
        effectDuration--;
        if (effectDuration == 0) {
            movementSpeed = 1;
            canCutHyphae = true;
        }
    }

    /**
     * Sets the current Tekton where the insect is located.
     * 
     * @param tekton The new Tekton.
     */
    public void setCurrentTekton(Tekton tekton) {
        currentTekton = tekton;
    }

    /**
     * Moves the insect to a target Tekton if it is connected to the current Tekton.
     * 
     * @param targetTekton The target Tekton where the insect wants to move.
     */
    public void move(Tekton targetTekton) {
        // TODO implement function

        System.out.println("[Insect] isConenctedTo(" + targetTekton + ") -> [Tekton]");

        if (currentTekton.isConnectedTo(targetTekton)) {
            System.out.println("[Insect] isConnectedTo(" + targetTekton + ") <- [Tekton] {true}");
            System.out.println("[Insect] setCurrentTekton(" + targetTekton + ") -> [Insect]");
            this.setCurrentTekton(targetTekton);
            return;
        }

        System.out.println("[Insect] isConnectedTo(" + targetTekton + ") <- [Tekton] {false}");
    }

    /**
     * Eats a spore and applies its effect to the insect.
     * 
     * @param spore The spore that the insect eats.
     */
    public void eatSpore(Spore spore) {
        System.out.println("[Insect] getNutrition() -> [Spore]");
        nutrition += spore.getNutrition();
        System.out.println("[Insect] getNutrition() <- [Spore] {" + nutrition + "}");

        System.out.println("[Insect] applyEffect(" + this + ") -> [Spore]");
        spore.applyEffect(this);
        currentTekton.removeSpore(spore);

    }

    /**
     * Cuts a hyphae and removes it from the connected Tektons and Myceliums.
     * 
     * @param hyphae The hyphae that the insect cuts.
     */
    public void cutHyphae(Hyphae hyphae) {
        if (canCutHyphae) {
            hyphae.setTimeToLive(1);
        }
    }

    /**
     * Returns the current nutrition level of the insect.
     * 
     * @return The nutrition level.
     */
    public float getNutrition() {
        return nutrition;
    }

    /**
     * Sets the movement speed of the insect.
     * 
     * @param movementSpeed The new movement speed.
     */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
    public float getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Sets whether the insect can cut hyphae.
     * 
     * @param canCutHyphae True if the insect can cut hyphae, false otherwise.
     */
    public void setCanCutHyphae(boolean canCutHyphae) {
        this.canCutHyphae = canCutHyphae;
    }

    /**
     * Returns whether the insect can cut hyphae.
     * 
     * @return True if the insect can cut hyphae, false otherwise.
     */
    public boolean getCanCutHyphae() {
        return canCutHyphae;
    }

    /**
     * Returns the current Tekton where the insect is located.
     * 
     * @return The current Tekton.
     */
    public Tekton getCurrentTekton() {
        return currentTekton;
    }

    /**
     * Copy constructor for the Insect class.
     * 
     * @param insect The insect to copy.
     */
    public Insect(Insect insect) {
        Insect copy = new Insect(insect.currentTekton, insect.movementSpeed);
        copy.owner = insect.owner;
        insect.getEntomologist().addInsect(copy);
        copy.nutrition = 0;
    }

    /**
     * Checks if the insect is stunned.
     * 
     * @return True if the insect is stunned, false otherwise.
     */
    public boolean isStunned() {
        if (movementSpeed == 0) {
            return true;
        }
        return false;
    }
}
