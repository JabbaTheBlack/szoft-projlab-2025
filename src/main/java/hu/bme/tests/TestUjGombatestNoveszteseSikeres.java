package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the successful growth of a new mycelium, on a tekton.
 * This test case simulates the interaction between mycelium, hyphae, a mycologist, and tekton structures.
 * 
 * @see Mycelium
 * @see Hyphae
 * @see Mycologist
 * @see DefensiveSpore
 * @see MultiTypeTekton
 */

public class TestUjGombatestNoveszteseSikeres {
    public static void test_uj_gombatest_novesztese_sikeres() {
        Mycelium m1 = new Mycelium();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();
        Mycologist mycologist = new Mycologist();
        DefensiveSpore s1 = new DefensiveSpore();
        DefensiveSpore s2  = new DefensiveSpore();
        DefensiveSpore s3 = new DefensiveSpore();
        
        m1.setCurrentTekton(t2);
        m1.addHyphae(h1);

        //mycologist.addMycelium(m1);

        t2.addMycelium(m1);
        t2.addHyphae(h1);
        t2.addHyphae(h2);

        h1.addMycelium(m1);
        h1.addHyphae(h2);
        h1.addCurrentTekton(t2);

        h2.addHyphae(h1);
        h2.addHyphae(h3);
        h2.addCurrentTekton(t1);
        h2.addCurrentTekton(t2);

        h3.addHyphae(h2);
        h3.addCurrentTekton(t1);

        t1.addHyphae(h3);
        t1.addSpore(s1);
        t1.addSpore(s2);
        t1.addSpore(s3);
        t1.connectToTekton(t2);
        t2.connectToTekton(t1);

        System.out.println("[Tester] growMycelium(" + h3 + ", " + t1 + ") -> [Mycologist]");
        mycologist.growMycelium(h3, t1);

    }
}
