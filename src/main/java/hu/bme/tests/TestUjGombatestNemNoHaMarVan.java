package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.SingleTypeTekton;

/**
 * Tests the scenario where a new mycelium cannot be grown if there is already one present.
 * 
 * This class simulates a network of tektons, myceliums, and hyphaes, and tests the behavior
 * when a mycologist attempts to grow a new mycelium on a tekton that already contains a mycelium.
 * 
 * @see Mycologist
 * @see Mycelium
 * @see Hyphae
 * @see DefensiveSpore
 * @see MultiTypeTekton
 * @see SingleTypeTekton
 */

public class TestUjGombatestNemNoHaMarVan {
    public static void test_uj_gombatest_nem_no_ha_mar_van() {
        SingleTypeTekton t1 = new SingleTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();

        Mycologist myc = new Mycologist();
        Mycelium m1 = new Mycelium();
        Mycelium m2 = new Mycelium();

        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();

        DefensiveSpore s1 = new DefensiveSpore();
        DefensiveSpore s2 = new DefensiveSpore();
        DefensiveSpore s3 = new DefensiveSpore();


        t2.addMycelium(m2);
        t2.addHyphae(h3);
        t2.addHyphae(h2);
        ///
        h1.addMycelium(m1);
        h1.addCurrentTekton(t1);
        h1.addHyphae(h2);

        h2.addHyphae(h3);
        h2.addCurrentTekton(t1);
        
        h2.addCurrentTekton(t2);
        h2.addHyphae(h1);

        h3.addHyphae(h2);
        h3.addMycelium(m2);
        h3.addCurrentTekton(t2);

        m2.addHyphae(h3);
        m2.addHyphae(h2);

        t1.addHyphae(h1);
        t1.addSpore(s1);
        t1.addSpore(s2);
        t1.addSpore(s3);
        t1.addMycelium(m1);

        m1.addHyphae(h1);
        m1.setCurrentTekton(t1);

        System.out.println("[Tester] growMycelium(" + h2 + t1 + ") -> [Mycologist]");
        myc.growMycelium(h2, t1);

    }
}
