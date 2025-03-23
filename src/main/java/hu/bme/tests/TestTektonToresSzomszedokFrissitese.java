package hu.bme.tests;

import hu.bme.managers.TektonManager;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the update of neighboring tekton structures when one tekton breaks apart.
 * This test case simulates the interaction between multiple tekton structures.
 * 
 * When one tekton breaks apart, the new tektons get the same type as the original tekton.
 * 
 * @see MultiTypeTekton
 */

public class TestTektonToresSzomszedokFrissitese {
    public static void test_tekton_tores_szomszedok_frissitese() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        MultiTypeTekton t3 = new MultiTypeTekton();
        TektonManager tm = new TektonManager();


        tm.addTekton(t1);
        tm.addTekton(t2);
        tm.addTekton(t3);
        t1.addNeighbour(t2);
        t2.addNeighbour(t1);
        t2.addNeighbour(t3);
        t3.addNeighbour(t2);

        tm.breakApart(t2);
    }
}
