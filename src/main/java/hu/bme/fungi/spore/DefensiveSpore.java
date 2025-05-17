package hu.bme.fungi.spore;

import hu.bme.fungi.Hyphae;
import hu.bme.insect.Insect;

/**
 * Class representing a DefensiveSpore.
 * A DefensiveSpore if eaten by an {@link Insect} will not let it cut a
 * {@link Hyphae} for a given time.
 */
public class DefensiveSpore extends Spore {

    /**
     * Initializes a DefensiveSpore.
     */
    public DefensiveSpore() {
        super();
        textureProvider.setImage("src/main/resources/images/fungi_images/defensive_spore.png");
    }

    public Spore clone() {
        return new DefensiveSpore();
    }

    /**
     * Initializes a DefensiveSpore with the given arguments
     * 
     * @param nutrition  Nutritional value of the spore
     * @param effectRate Effect Rate of the spore
     */
    public DefensiveSpore(float nutrition, float effectRate) {
        super(nutrition, effectRate);
    }

    /**
     * Applies the effect of the DefensiveSpore on an {@link Insect}.
     * 
     * @param insect the insect to apply the effect on
     */
    @Override
    public void applyEffect(Insect insect) {
        System.out.println("[DefensiveSpore] setCanCutHyphae(false) -> [" + insect + "]");
        insect.setCanCutHyphae(false);
    }
}
