package hu.bme.core;

import java.util.ArrayList;
import java.util.List;

import hu.bme.interfaces.ITickable;
/**
 * The Ticker class implements the Singleton design pattern and serves as a central manager
 * for notifying registered observers about periodic "tick" events.
 * This class ensures that only one instance of {@code Ticker} exists throughout the application
 * and provides a global point of access to it.
 */
public class Ticker {
    private List<ITickable> observers;
    private static volatile Ticker instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the list of observers.
     */
    private Ticker() {
        observers = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the Ticker class.
     * This method uses double-checked locking to ensure thread safety while minimizing synchronization overhead.
     *
     * @return The singleton instance of Ticker.
     */
    public static Ticker getInstance() {
        Ticker result = instance;

        if(result == null) {
            synchronized(Ticker.class){
                result = instance;
                if(result == null){
                    instance = result = new Ticker();
                }
            }
        }

        return result;
    }

    /**
     * Adds an observer to the list of tickable objects.
     *
     * @param tickable The observer to be added.
     */
    public void addObserver(ITickable tickable) {
        observers.add(tickable);
    }

     /**
     * Removes an observer from the list of tickable objects.
     *
     * @param tickable The observer to be removed.
     */
    public void removeObserver(ITickable tickable) {
        observers.remove(tickable);
    }

    /**
     * Notifies all registered observers by invoking their @link tick() method.
     *
     * This method iterates through all observers in the list and triggers their tick behavior.
     */
    public void tick(){
        for(ITickable observer : observers) {
            observer.tick();
        }
    }
}
