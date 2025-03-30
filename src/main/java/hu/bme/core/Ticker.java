package hu.bme.core;

import java.util.ArrayList;
import java.util.List;

import hu.bme.interfaces.ITickable;
//TODO javadoc
public class Ticker {
    private List<ITickable> observers;
    private static volatile Ticker instance;

    private Ticker() {
        observers = new ArrayList<>();
    }

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

    public void addObserver(ITickable tickable) {
        observers.add(tickable);
    }

    public void removeObserver(ITickable tickable) {
        observers.remove(tickable);
    }

    public void tick(){
        for(ITickable observer : observers) {
            observer.tick();
        }
    }
}
