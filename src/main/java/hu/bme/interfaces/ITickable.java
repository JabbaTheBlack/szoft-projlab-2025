package hu.bme.interfaces;

/**
 * Represents an entity that can perform time-based updates in a simulation.
 * Classes implementing this interface must define behavior for each simulation tick.
 */
public interface ITickable {
    /**
     * Advances the state of the implementing entity by one simulation tick.
     * This method is called periodically to simulate time progression.
     */
    void tick();
}
