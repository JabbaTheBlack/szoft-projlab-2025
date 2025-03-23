package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the growth of hyphae to a non-adjacent tekton structure, it will fail
 * This test case simulates the interaction between a mycologist, mycelium, hyphae, and tekton structures.
 * 
 * @see Mycologist
 * @see Mycelium
 * @see Hyphae
 * @see MultiTypeTekton
 */

public class TestGombaFonalNovesztesNemSzomszedosTekton {
    public static void TestGombaFonalNovesztesNemSzomszedosTekton(){
        Mycologist myc1 = new Mycologist();
        Mycelium m1 = new Mycelium();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        Hyphae h1 = new Hyphae();

        myc1.addMycelium(m1);
        m1.setCurrentTekton(t2);
        m1.addHyphae(h1);
        h1.addMycelium(m1);
        h1.addCurrentTekton(t2);
        t2.addMycelium(m1);
        t2.addHyphae(h1);

        
        System.out.println("[Tester] growHyphaeToTekton("+h1+", "+t1+") -> [Mycologist]");
        myc1.growHyphaeToTekton(h1, t1);
    }
}
