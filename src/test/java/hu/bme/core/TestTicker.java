package hu.bme.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycologist;
import hu.bme.interfaces.ITickable;
import hu.bme.managers.MycologistManager;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.Tekton;

class TestTicker {
    private Ticker ticker;
    private ITickable mockTickable1;
    private ITickable mockTickable2;

    @BeforeEach
    void setUp() 
    {
        ticker = Ticker.getInstance();

        mockTickable1 = Mockito.mock(ITickable.class);
        mockTickable2 = Mockito.mock(ITickable.class);
    }

    @Test
    void getInstance() throws InterruptedException{
        Ticker firstInstance = Ticker.getInstance();
        Ticker secondInstance = Ticker.getInstance();

        assertSame(firstInstance, secondInstance);

        final Ticker[] instances = new Ticker[2];
        Thread thread1 = new Thread(() -> instances[0] = Ticker.getInstance());
        Thread thread2 = new Thread(() -> instances[1] = Ticker.getInstance());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertNotNull(thread1);
        assertNotNull(thread2);
        assertSame(instances[0], instances[1]);

    }

    @Test
    void addRemoveObserver() {
        ticker.addObserver(mockTickable1);
        ticker.removeObserver(mockTickable2); 

        assertEquals(1, getObserversCount(ticker));

        ticker.addObserver(mockTickable2);

        assertEquals(2, getObserversCount(ticker));
    }

    private int getObserversCount(Ticker ticker) {
        try {
            java.lang.reflect.Field field = Ticker.class.getDeclaredField("observers");
            field.setAccessible(true);
            return ((java.util.List<?>) field.get(ticker)).size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getHyphaeCount(Mycologist mycologist) {
        try {
            // Get the field that stores hyphae in the Mycologist class
            java.lang.reflect.Field field = Mycologist.class.getDeclaredField("hyphaes");
            field.setAccessible(true);
            
            // Assuming hyphae is stored as a Collection or List
            return ((java.util.Collection<?>) field.get(mycologist)).size();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get hyphae count via reflection", e);
        }
    }

}
