package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.tekton.MultiTypeTekton;

public class TestTektonNemTorhetGombaTestMiatt {
    
    public static void test_tekton_nem_torhet_gombatest_miatt() {
        Mycelium m1 = new Mycelium();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        DefensiveSpore s1 = new DefensiveSpore();
        DefensiveSpore s2 = new DefensiveSpore();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();


        t1.addHyphae(h2);
        h2.setCurrentTekton(t2);
        t1.addSpore(s1);

        m1.setCurrentTekton(t2);
        t2.addMycelium(m1);
        t2.addHyphae(h1);
        h1.setCurrentTekton(t2);
        t1.addSpore(s2);

        System.out.println("[Tester] breakApart(" + h1 + "," + t2 +") -> [" + t1 + "]");
        t1.breakApart();
        
        System.out.println("[Tester] breakApart(" + h1 + "," + t2 +") -> [" + t2 + "]");
        t2.breakApart();
    }
}
