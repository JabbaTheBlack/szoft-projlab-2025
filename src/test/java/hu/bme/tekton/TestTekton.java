package hu.bme.tekton;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestTekton {
    private Tekton tekton1;
    private Tekton tekton2;
    private Tekton tekton3;

    @BeforeEach
    void setUp() {
        tekton1 = new AbsrobingTekton();
        tekton2 = new KeeperTekton();
        tekton3 = new MultiTypeTekton();
    }

    @Test
    void testAddNeighbour() {
        tekton1.addNeighbour(tekton2);
        List<Tekton> neighbours = tekton1.getNeighbours();

        assertEquals(1, neighbours.size());
        assertTrue(neighbours.contains(tekton2));
    }

    @Test
    void testAddNeighbourBidirectional() {
        tekton1.addNeighbour(tekton2);

        assertTrue(tekton1.getNeighbours().contains(tekton2));
        assertTrue(tekton2.getNeighbours().contains(tekton1));
    }

    @Test
    void testAddDuplicateNeighbour() {
        tekton1.addNeighbour(tekton2);
        tekton1.addNeighbour(tekton2);

        assertEquals(1, tekton1.getNeighbours().size());
    }

    @Test
    void testConnectToTekton() {
        tekton1.connectToTekton(tekton2);

        assertTrue(tekton1.getNeighbours().contains(tekton2));
        assertTrue(tekton2.getNeighbours().contains(tekton1));
    }

    @Test
    void testConnectToTektonBidirectional() {
        tekton1.connectToTekton(tekton2);
        tekton2.connectToTekton(tekton3);

        assertTrue(tekton1.getNeighbours().contains(tekton2));
        assertTrue(tekton2.getNeighbours().contains(tekton3));
        assertFalse(tekton1.getNeighbours().contains(tekton3));
    }

    @Test
    void testAddHyphae() {
        Hyphae hyphae = new Hyphae();
        boolean added = tekton1.addHyphae(hyphae);

        assertTrue(added);
        assertTrue(tekton1.getHyphaes().contains(hyphae));
    }

    @Test
    void testAddDuplicateHyphae() {
        Hyphae hyphae = new Hyphae();
        tekton1.addHyphae(hyphae);
        boolean addedAgain = tekton1.addHyphae(hyphae);

        assertFalse(addedAgain);
        assertEquals(1, tekton1.getHyphaes().size());
    }

    @Test
    void testGetNeighbours() {
        tekton1.addNeighbour(tekton2);
        tekton1.addNeighbour(tekton3);

        List<Tekton> neighbours = tekton1.getNeighbours();
        assertEquals(2, neighbours.size());
        assertTrue(neighbours.contains(tekton2));
        assertTrue(neighbours.contains(tekton3));
    }

    @Test
    void testGetHyphae() {
        Hyphae hyphae1 = new Hyphae();
        Hyphae hyphae2 = new Hyphae();

        tekton1.addHyphae(hyphae1);
        tekton1.addHyphae(hyphae2);

        List<Hyphae> hyphaeList = tekton1.getHyphaes();
        assertEquals(2, hyphaeList.size());
        assertTrue(hyphaeList.contains(hyphae1));
        assertTrue(hyphaeList.contains(hyphae2));
    }

    @Test
    void testNoDuplicateNeighbours() {
        tekton1.addNeighbour(tekton2);
        tekton1.addNeighbour(tekton2);

        assertEquals(1, tekton1.getNeighbours().size());
    }

    @Test
    void testNoSelfNeighbour() {
        tekton1.addNeighbour(tekton1);

        assertFalse(tekton1.getNeighbours().contains(tekton1));
    }

    @Test
    void testMyceliumFreeTekton(){
        MyceliumFreeTekton mFreeTekton = new MyceliumFreeTekton();
        KeeperTekton keeperTekton = new KeeperTekton();

        mFreeTekton.addNeighbour(keeperTekton);

        
        Mycelium mycelium = new Mycelium();
        mycelium.setCurrentTekton(keeperTekton);

        Mycologist mycologist = new Mycologist();

        mycologist.addMycelium(mycelium);
        mycologist.chooseSpore(2);

        Hyphae h1 = new Hyphae();
        h1.addCurrentTekton(keeperTekton);
        keeperTekton.addHyphae(h1);

        Hyphae h2 = new Hyphae();
        h2.addCurrentTekton(keeperTekton);
        h2.addCurrentTekton(mFreeTekton);

        Hyphae h3 = new Hyphae();
        h3.addCurrentTekton(mFreeTekton);

       
        mycologist.growSpore(mycelium);
        mycologist.growSpore(mycelium);
        mycologist.growSpore(mycelium);
        
        assertEquals(3, mycelium.getSporeCount());

        mycologist.releaseSpore(mycelium);
        mycologist.releaseSpore(mycelium);
        mycologist.releaseSpore(mycelium);

        assertEquals(0, mycelium.getSporeCount());
        assertEquals(3, mFreeTekton.getSporeCount());

        mycologist.growMycelium(h3, mFreeTekton);

        assertEquals(1, mycologist.getMyceliums().size());
        assertEquals(0, mFreeTekton.fungalManager.getMyceliumCount());
    }
}