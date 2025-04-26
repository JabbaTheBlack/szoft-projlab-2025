package hu.bme.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.Spore;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.tekton.AbsrobingTekton;
import hu.bme.tekton.KeeperTekton;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.MyceliumFreeTekton;
import hu.bme.tekton.SingleTypeTekton;
import hu.bme.tekton.Tekton;

class TestSteveTest {
    private Mycologist mycologist1;
    private Mycologist mycologist2;
    
    private Entomologist entomologist;
    private Tekton t0, t1, t2, t3, t4, t5, t6;

    private Mycelium mycelium1, mycelium2;
    private Insect insect;


    @BeforeEach
    void setUp() {
        // Create Tektons
        t0 = new MultiTypeTekton();
        t1 = new AbsrobingTekton();
        t2 = new KeeperTekton();
        t3 = new MultiTypeTekton();
        t4 = new SingleTypeTekton();
        t5 = new MyceliumFreeTekton();
        t6 = new SingleTypeTekton();

        // Connect Tektons
        t0.addNeighbour(t6);
        t0.addNeighbour(t3);
        t0.addNeighbour(t1);

        t1.addNeighbour(t3);
        t1.addNeighbour(t2);
        t1.addNeighbour(t4);
        
        t3.addNeighbour(t6);
        t3.addNeighbour(t5);
        t3.addNeighbour(t2);
        
        t6.addNeighbour(t5);
        
        t4.addNeighbour(t2);
        t4.addNeighbour(t5);

        // Create Players
        mycologist1 = new Mycologist();
        mycologist2 = new Mycologist();
        entomologist = new Entomologist();

        // Set Mycelium Types
        mycologist1.chooseSpore(1);
        mycologist2.chooseSpore(3);

        // Add Mycelium
        mycelium1 = new Mycelium(t0);
        mycelium2 = new Mycelium(t6);
        
        mycologist1.addMycelium(mycelium1);
        mycelium1.setCurrentTekton(t0);
        t0.addMycelium(mycelium1);

        mycologist2.addMycelium(mycelium2);
        t6.addMycelium(mycelium2);
        mycelium2.setCurrentTekton(t6);

        // Add Insect
        insect = new Insect();
        entomologist.addInsect(insect);
        insect.setCurrentTekton(t3);
    }

    @Test
    void testScenario() {
        // Add Spores
        Spore stunSpore = new StunSpore();
        t1.addSpore(stunSpore);

        // Grow Hyphae from Mycelium
        mycologist1.growHyphaeOnTekton(mycelium1, t3);
        mycologist1.growHyphaeOnTekton(mycelium1, t1);
        mycologist1.growHyphaeOnTekton(mycelium1, t2);

        mycologist2.growHyphaeOnTekton(mycelium2, t3);
        mycologist2.growHyphaeOnTekton(mycelium2, t5);
        mycologist2.growHyphaeOnTekton(mycelium2, t4);

        verifyT0(t0);
        verifyT1(t1);
        verifyT2(t2);
        verifyT3(t3);
        verifyT4(t4);
        verifyT5(t5);
        verifyT6(t6);

        Hyphae h0 = new Hyphae();
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
        Hyphae h11 = new Hyphae();

        t3.addHyphae(h0);
        t3.addHyphae(h1);
        t1.addHyphae(h2);
        t1.addHyphae(h3);
        t2.addHyphae(h4);
        t2.addHyphae(h5);
        t6.addHyphae(h6);
        t3.addHyphae(h7);
        t3.addHyphae(h8);
        t5.addHyphae(h9);
        t5.addHyphae(h10);
        t4.addHyphae(h11);

        // Step 10: Move Insect
        insect.setCurrentTekton(t1);

        // Step 11: Eat Spore
        insect.eatSpore(stunSpore);

        // Verify Spore is Removed
        assertFalse(t1.getSpores().contains(stunSpore));

        // Step 12: Eat Insect
        mycologist1.eatInsect(insect);

        // Verify Insect is Removed
        assertNull(insect.getCurrentTekton());
        assertFalse(entomologist.getInsects().contains(insect));
    
        
    }

    private void verifyT0(Tekton t0) {
        assertTrue(t0.getNeighbours().contains(t1));
        assertTrue(t0.getNeighbours().contains(t3));
        assertTrue(t0.getNeighbours().contains(t6));

        assertFalse(t0.getNeighbours().contains(t2));
        assertFalse(t0.getNeighbours().contains(t4));
        assertFalse(t0.getNeighbours().contains(t5));
    }

    private void verifyT1(Tekton t1) {
        assertTrue(t1.getNeighbours().contains(t0));
        assertTrue(t1.getNeighbours().contains(t2));
        assertTrue(t1.getNeighbours().contains(t3));
        assertTrue(t1.getNeighbours().contains(t4));
      
        assertFalse(t1.getNeighbours().contains(t1));
        assertFalse(t1.getNeighbours().contains(t5));
        assertFalse(t1.getNeighbours().contains(t6));
    }

    private void verifyT2(Tekton t2) {
        assertTrue(t2.getNeighbours().contains(t1));
        assertTrue(t2.getNeighbours().contains(t3));
        assertTrue(t2.getNeighbours().contains(t4));
        
        assertFalse(t2.getNeighbours().contains(t0));
        assertFalse(t2.getNeighbours().contains(t6));
    }

    private void verifyT3(Tekton t3) {
        
        assertTrue(t3.getNeighbours().contains(t1));
        assertTrue(t3.getNeighbours().contains(t2));
        assertTrue(t3.getNeighbours().contains(t5));
        assertTrue(t3.getNeighbours().contains(t5));
        assertTrue(t3.getNeighbours().contains(t0));
        
        
        assertFalse(t3.getNeighbours().contains(t4));
        assertFalse(t3.getNeighbours().contains(t3));
    }
    private void verifyT4(Tekton t4) {
        assertTrue(t4.getNeighbours().contains(t1));
        assertTrue(t4.getNeighbours().contains(t2));
        assertTrue(t4.getNeighbours().contains(t5));

        assertFalse(t4.getNeighbours().contains(t0));
        assertFalse(t4.getNeighbours().contains(t3));
        assertFalse(t4.getNeighbours().contains(t4));
        assertFalse(t4.getNeighbours().contains(t6));
    }

    private void verifyT5(Tekton t5) {
        assertTrue(t5.getNeighbours().contains(t6));
        assertTrue(t5.getNeighbours().contains(t3));
        assertTrue(t5.getNeighbours().contains(t4));

        assertFalse(t5.getNeighbours().contains(t0));
        assertFalse(t5.getNeighbours().contains(t1));
        assertFalse(t5.getNeighbours().contains(t2));
        assertFalse(t5.getNeighbours().contains(t5));
    }
    private void verifyT6(Tekton t6) {
        assertTrue(t6.getNeighbours().contains(t5));
        assertTrue(t6.getNeighbours().contains(t3));
        assertTrue(t6.getNeighbours().contains(t0));

        assertFalse(t6.getNeighbours().contains(t6));
        assertFalse(t6.getNeighbours().contains(t1));
        assertFalse(t6.getNeighbours().contains(t2));
        assertFalse(t6.getNeighbours().contains(t4));
    }
}
