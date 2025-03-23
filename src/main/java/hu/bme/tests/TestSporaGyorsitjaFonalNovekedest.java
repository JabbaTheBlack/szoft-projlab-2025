package hu.bme.tests;

import hu.bme.fungi.*;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.tekton.MultiTypeTekton;    

/**
 * Tests the scenario where a spore accelerates the growth of a hyphae on a given tekton.
 * 
 * This class simulates a network of myceliums, hyphaes, and tektons, and tests the behavior
 * when a mycologist grows a hyphae on a tekton in the presence of spores.
 * 
 * @see Mycelium
 * @see Mycologist
 * @see Hyphae
 * @see MultiTypeTekton
 * @see StunSpore
 */

public class TestSporaGyorsitjaFonalNovekedest {
    public static void test_spora_gyorsitja_fonal_novekedest() {
        Mycelium m1 = new Mycelium();
        Mycologist myc = new Mycologist();
        Hyphae h1 = new Hyphae();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        StunSpore s1 = new StunSpore();
        StunSpore s2 = new StunSpore();
        StunSpore s3 = new StunSpore();
        StunSpore s4 = new StunSpore();

        m1.addHyphae(h1);
        m1.setCurrentTekton(t1);

        t1.addMycelium(m1);
        t1.addHyphae(h1);
        t1.addNeighbour(t2);
        t1.addSpore(s1);
        t1.addSpore(s2);
        t1.addSpore(s3);
        t1.addSpore(s4);

        t2.addNeighbour(t2);

        h1.addMycelium(m1);
        h1.addCurrentTekton(t1);

        //myc.addMycelium(m1);

        System.out.println("[Tester] growHyphaeToTekton(" + h1 + "," + t2 +") -> [Mycologist]");
        myc.growHyphaeToTekton(h1, t2);
    }
}
