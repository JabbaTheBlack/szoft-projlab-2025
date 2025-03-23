package hu.bme.tests;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the scenario where spores are released from an upgraded mycelium to a neighboring tekton's neighbor.
 * 
 * This class simulates a network of myceliums and tektons, and tests the behavior when an upgraded mycelium releases spores.
 * 
 * @see Mycelium
 * @see StunSpore
 * @see MultiTypeTekton
 */

public class TestSporeKiloveseSzomszeddalSzomszedosTektonra {
    public static void test_spora_kilovese_szomszeddal_szomszedos_tektonra() {
        Mycelium m1 = new Mycelium();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        MultiTypeTekton t3 = new MultiTypeTekton();
        StunSpore s1 = new StunSpore();

        m1.setCurrentTekton(t2);
        m1.upgrade();
        m1.addSpore(s1);

        t1.addMycelium(m1);
        t1.addNeighbour(t2);

        t2.addNeighbour(t1);
        t2.addNeighbour(t3);

        t3.addNeighbour(t2);

        m1.releaseSpores();
    }
}
