package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the growth of a hyphae to a MultiTypeTekton.
 * 
 * This class simulates a scenario where a mycologist grows a hyphae from one MultiTypeTekton to another.
 * 
 * @see Hyphae
 * @see Mycelium
 * @see Mycologist
 * @see MultiTypeTekton
 */

public class TestGombaFonalNovesztesMultiTypeTektonon {
        
    public static void test_gombafonal_novesztes_multi_type_tektonon() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        Mycologist mycologis1 = new Mycologist();
        Mycologist mycologist2 = new Mycologist();

        Mycelium m1 = new Mycelium();
        Mycelium m2 = new Mycelium();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();

        mycologis1.addMycelium(m1);
        mycologist2.addMycelium(m2);

        t1.addNeighbour(t2);
        t2.addNeighbour(t1);

        t2.addMycelium(m2);
        t2.addHyphae(h2);
        t2.addHyphae(h3);

        t1.addMycelium(m1);
        t1.addHyphae(h1);
        t1.addHyphae(h3);

        m1.setCurrentTekton(t1);
        m2.setCurrentTekton(t2);
        m1.addHyphae(h1);
        m2.addHyphae(h2);

        h1.addMycelium(m1);
        h2.addMycelium(m2);
        
        h2.addHyphae(h3);
        h3.addHyphae(h2);
        h1.addCurrentTekton(t1);
        h2.addCurrentTekton(t2);
        h3.addCurrentTekton(t2);
        h3.addCurrentTekton(t1);

        System.out.println("[Tester] growHyphaeToTekton(" + h3 +", "+ t1 + ") -> [Mycologist]");
        mycologis1.growHyphaeToTekton(h3, t1);
    }
}
