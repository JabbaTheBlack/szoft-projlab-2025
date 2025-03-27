package hu.bme.fungi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hu.bme.tekton.Tekton;

class TestHyphae {
    private Hyphae hyphae;

    @Mock
    private Mycelium mycelium1, mycelium2;

    @Mock
    private Tekton tekotn1, tekton2;

    @Mock
    Mycologist mycologist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hyphae = new Hyphae();
    }

    @Test
    void testSetAndGetOwner() {
        hyphae.setOwner(mycologist);
        assertEquals(mycologist, hyphae.getOwner());
    }

    @Test
    void testAddAndRemoveMycelium() {
        hyphae.addMycelium(mycelium1);
        hyphae.addMycelium(mycelium2);

        List<Mycelium> connectedMyceliums = hyphae.getConnectedMyceliums();
        assertTrue(connectedMyceliums.contains(mycelium1));
        assertTrue(connectedMyceliums.contains(mycelium2));

        hyphae.removeMycelium(mycelium1);
        assertFalse(hyphae.getConnectedMyceliums().contains(mycelium1));
    }

    @Test
    void testAddAndRemoveHyphae() {
        Hyphae hyphae1 = new Hyphae();
        Hyphae hyphae2 = new Hyphae();

        hyphae.addHyphae(hyphae1);
        hyphae.addHyphae(hyphae2);

        List<Hyphae> connectedHyphaes = hyphae.getConnectedHyphae();
        assertTrue(connectedHyphaes.contains(hyphae1));
        assertTrue(connectedHyphaes.contains(hyphae2));

        hyphae.removeHyphae(hyphae2);
        assertFalse(hyphae.getConnectedHyphae().contains(hyphae2));
    }

    @Test
    void testGetConnectedHyphaeUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> {
            hyphae.getConnectedHyphae().add(new Hyphae());
        }, "Should not be able to modify the connectedHyphae list.");
    }

    @Test
    void testGetConnectedMyceliumsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> {
            hyphae.getConnectedMyceliums().add(mycelium1);
        }, "Should not be able to modify the connectedMyceliums list.");
    }

    @Test
    void testAddAndRemoveTekton() {
        hyphae.addCurrentTekton(tekotn1);
        hyphae.addCurrentTekton(tekton2);

        List<Tekton> tektons = hyphae.getCurrentTekton();
        assertTrue(tektons.contains(tekotn1));
        assertTrue(tektons.contains(tekton2));

        hyphae.removeCurrentTekton(tekotn1);
        assertFalse(hyphae.getCurrentTekton().contains(tekotn1), "Tekton should be removed.");
    }

    @Test
    void testIsConnectedToMycelium(){
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();
        Hyphae h4 = new Hyphae();
        Hyphae h5 = new Hyphae();
        Hyphae h6 = new Hyphae();
        Hyphae h7 = new Hyphae();
        Hyphae h8 = new Hyphae();
        Hyphae h9 = new Hyphae();
        Hyphae h10 = new Hyphae();

        Mycelium mycelium = new Mycelium<>();

        h1.addHyphae(h2);

        h2.addHyphae(h1);
        h2.addHyphae(h3);
        
        h3.addHyphae(h4);
        h3.addHyphae(h2);

        h4.addHyphae(h3);
        h4.addHyphae(h5);

        h5.addHyphae(h4);
        h5.addHyphae(h6);
        
        h6.addHyphae(h5);
        h6.addHyphae(h7);
        
        h7.addHyphae(h6);
        h7.addHyphae(h8);
        
        h8.addHyphae(h7);
        h8.addHyphae(h9);
        
        h9.addHyphae(h8);
        h9.addHyphae(h10);

        h10.addHyphae(h9);

        assertFalse(h1.isConnectedToMycelium());
        assertFalse(h5.isConnectedToMycelium());
        assertFalse(h10.isConnectedToMycelium());

        h6.addMycelium(mycelium);

        assertTrue(h1.isConnectedToMycelium());
        assertTrue(h5.isConnectedToMycelium());
        assertTrue(h10.isConnectedToMycelium());

        Hyphae isolatedHyphae = new Hyphae();
        assertFalse(isolatedHyphae.isConnectedToMycelium());

        isolatedHyphae.addHyphae(h1);
        h1.addHyphae(isolatedHyphae);
        assertTrue(isolatedHyphae.isConnectedToMycelium());
    }

    @Test
    void testTickDecrementsTimeToLive() {
        hyphae.setTimeToLive(3);
        hyphae.tick();
        assertEquals(2, hyphae.getTimeToLive());
    }

    @Test
    void testTickRemovesHyphaeWhenTimeToLiveExpires(){
        Hyphae neighbour = new Hyphae();
        Mycelium mycelium = new Mycelium<>();

        hyphae.addHyphae(neighbour);
        neighbour.addHyphae(neighbour);
        hyphae.addMycelium(mycelium);

        hyphae.setTimeToLive(1);
        hyphae.tick();
    
        assertFalse(neighbour.getConnectedHyphae().contains(hyphae));
    }

    @Test
    void testAddRemoveTekton() {
        hyphae.addCurrentTekton(tekotn1);
        assertTrue(hyphae.getCurrentTekton().contains(tekotn1));

        hyphae.removeCurrentTekton(tekotn1);
        assertFalse(hyphae.getCurrentTekton().contains(tekotn1));
    }

}
