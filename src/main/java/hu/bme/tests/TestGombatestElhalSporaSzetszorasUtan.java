package hu.bme.tests;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.managers.MyceliumManager;
import hu.bme.managers.SporeManager;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the scenario where a mycelium releases spores a number of times, after the releases the Mycelium dies.
 * 
 * This class simulates a network of myceliums and MultiTypeTektons, and tests the behavior
 * when a mycelium releases a spore.
 * 
 * @see Mycelium
 * @see Mycologist
 * @see DefensiveSpore
 * @see MultiTypeTekton
 */

public class TestGombatestElhalSporaSzetszorasUtan {
    public static void TestGombatestElhalSporaSzetszorasUtan(){
        Mycelium m1 = new Mycelium();
        Mycologist myc = new Mycologist();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        DefensiveSpore s1 = new DefensiveSpore();

        t1.addNeighbour(t2);
        t2.addNeighbour(t1);

        myc.addMycelium(m1);
        m1.setCurrentTekton(t1);
        t1.addMycelium(m1);
        m1.addSpore(s1);

        System.out.println("[Tester] releaseSpore(" + m1 + ") -> [Mycelium]");
        myc.releaseSpore(m1);
        
    }
}
