package hu.bme.tests;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.Tekton;

/**
 * Tests the scenario where spores are released from an unupgraded mycelium to neighboring tektons.
 * 
 * This class simulates a network of myceliums and tektons, and tests the behavior when an unupgraded mycelium releases spores.
 * 
 * @see Mycelium
 * @see StunSpore
 * @see MultiTypeTekton
 */

public class TestSporaKiloveseSzomszedosTektonra {
    public static void test_spora_kilovese_szomszedos_tektonra() {
        Mycelium m1 = new Mycelium();
        StunSpore s1 = new StunSpore();
        StunSpore s2 = new StunSpore();
        StunSpore s3 = new StunSpore();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        MultiTypeTekton t3 = new MultiTypeTekton();

        m1.setCurrentTekton(t2);
        m1.addSpore(s1);
        m1.addSpore(s2);
        m1.addSpore(s3);

        t1.addNeighbour(t2);

        t2.addMycelium(m1);
        t2.addNeighbour(t3);
        t2.addNeighbour(t1);

        t3.addNeighbour(t2);

        System.out.println("[Tester] releaseSpores() -> [Mycologist]");
        m1.releaseSpores();
    }
}
