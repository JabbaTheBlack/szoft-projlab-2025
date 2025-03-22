package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.managers.TektonManager;
import hu.bme.tekton.MultiTypeTekton;

public class TestGombafonalElszakadasTektonToresnel {
    public static void TestGombafonalElszakadasTektonToresnel() {
        TektonManager TM = new TektonManager();
        MultiTypeTekton t2 = new MultiTypeTekton();
        Mycelium m2 = new Mycelium();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();
        
        TM.addTekton(t2);
        t2.addMycelium(m2);
        t2.addHyphae(h1);
        t2.addHyphae(h2);
        t2.addHyphae(h3);
        m2.setCurrentTekton(t2);
        m2.addHyphae(h1);
        h1.addMycelium(m2);
        h1.addCurrentTekton(t2);
        h2.addCurrentTekton(t2);
        h3.addCurrentTekton(t2);

        t2.breakApart();
    }
}
