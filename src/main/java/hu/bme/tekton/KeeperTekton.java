package hu.bme.tekton;

import hu.bme.fungi.Hyphae;

/**
 * A specialized Tekton that maintains hyphae indefinitely by marking them as
 * persistent.
 * This class extends the base Tekton functionality to provide additional
 * support for fungal networks.
 */
public class KeeperTekton extends Tekton {

    /**
     * Constructs a KeeperTekton with default Tekton properties.
     */
    public KeeperTekton() {
        super();
    }

    /**
     * Adds a hyphae to this KeeperTekton and marks it as persistent.
     * Hyphae connected to a KeeperTekton will not decay unless explicitly removed.
     *
     * @param hyphae The hyphae to be added to this KeeperTekton.
     * @return true if the hyphae was successfully added, false otherwise.
     */
    @Override
    public boolean addHyphae(Hyphae hyphae) {
        fungalManager.addHyphae(hyphae);
        hyphae.setIsOnKeeperTekton(true);
        return true;

    }

    /**
     * Creates a new KeeperTekton.
     *
     * @return A new KeeperTekton with default configuration.
     */
    @Override
    public Tekton createTekton() {
        return new KeeperTekton();
    }
}
