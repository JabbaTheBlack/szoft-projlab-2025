package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.tekton.AbsrobingTekton;

/**
 * Tests the absorption of hyphae by an absorbing tekton structure.
 * This test case simulates the interaction between mycelium, hyphae, and an absorbing tekton.
 * 
 * @see Mycelium
 * @see Hyphae
 * @see AbsrobingTekton
 */

public class TestGombaFonalFelszivodasa {
    public static void test_gombafonal_felszivodasa() {
        Mycelium m1 = new Mycelium();
        AbsrobingTekton t1 = new AbsrobingTekton();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();

        m1.setCurrentTekton(t1);
        m1.addHyphae(h1);
        t1.addMycelium(m1);

        h1.addMycelium(m1);

        h1.addHyphae(h2);
        h2.addHyphae(h1);
        h2.addHyphae(h3);
        h3.addHyphae(h2);
        t1.addHyphae(h1);
        t1.addHyphae(h2);
        t1.addHyphae(h3);

        System.out.println("[Tester] absorbHyphae() -> [" + t1 + "]");
        t1.absorbHyphae();

    }

}
