package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the scenario where a tekton cannot be broken apart due to fungal presence.
 * 
 * This class simulates a network of myceliums, hyphaes, and tektons, and tests the behavior
 * when attempting to break apart a tekton that contains fungal elements.
 * 
 * @see Mycelium
 * @see Hyphae
 * @see DefensiveSpore
 * @see MultiTypeTekton
 */

public class TestTektonNemTorhetGombaTestMiatt {
    
    public static void test_tekton_nem_torhet_gombatest_miatt() {
        Mycelium m1 = new Mycelium();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        DefensiveSpore s1 = new DefensiveSpore();
        DefensiveSpore s2 = new DefensiveSpore();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        TektonManager tm = new TektonManager();

        tm.addTekton(t1);
        tm.addTekton(t2);

        t2.addHyphae(h2);
        h2.addCurrentTekton(t2);
        t1.addSpore(s1);

        m1.setCurrentTekton(t2);
        t2.addMycelium(m1);
        t2.addHyphae(h1);
        h1.addCurrentTekton(t2);
        t1.addSpore(s2);

        System.out.println("[Tester] breakApart() -> [" + t2 + "]");
        tm.breakApart(t2);
        
        
        System.out.println("[Tester] breakApart() -> [" + t1 + "]");
        tm.breakApart(t1);
    }
}
