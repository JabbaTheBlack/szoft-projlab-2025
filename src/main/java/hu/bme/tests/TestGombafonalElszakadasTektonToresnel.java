package hu.bme.tests;

import java.util.List;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.Tekton;

/**
 * This test case simulates the interaction between hyphae, mycelium, and tekton structures.
 * When a tekton breaks apart, the hyphae structures should be updated accordingly.
 * 
 * @see TektonManager
 * @see Mycelium
 * @see Hyphae
 * @see MultiTypeTekton
 */

public class TestGombafonalElszakadasTektonToresnel {
    public static void TestGombafonalElszakadasTektonToresnel() {
        TektonManager TM = new TektonManager();
        MultiTypeTekton t2 = new MultiTypeTekton();
        MultiTypeTekton t1 = new MultiTypeTekton();
        Mycelium m2 = new Mycelium();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();
        
        TM.addTekton(t2);
        TM.addTekton(t1);
        t2.addMycelium(m2);
        t2.addHyphae(h1);
        t2.addHyphae(h2);
        t1.addHyphae(h2);
        t1.addHyphae(h3);
        m2.setCurrentTekton(t2);
        m2.addHyphae(h1);
        h1.addMycelium(m2);
        h1.addCurrentTekton(t2);
        h2.addCurrentTekton(t2);
        h2.addCurrentTekton(t1);
        h3.addCurrentTekton(t1);
        h1.addHyphae(h2);
        h2.addHyphae(h1);
        h2.addHyphae(h3);
        h3.addHyphae(h2);

        System.out.println("[Tester] breakApart("+t1+") -> [TektonManager]");
        TM.breakApart(t1);
    }
}
