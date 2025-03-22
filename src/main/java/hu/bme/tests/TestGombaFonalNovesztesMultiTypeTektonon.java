package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.tekton.MultiTypeTekton;

public class TestGombaFonalNovesztesMultiTypeTektonon {
        
    public static void test_gombafonal_novesztes_multi_type_tektonon() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        Mycologist mycologist = new Mycologist();
        Mycelium m1 = new Mycelium();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();

        mycologist.addMycelium(m1);

        t1.addNeighbour(t2);

        t2.addNeighbour(t2);
        t2.addMycelium(m1);
        t2.addHyphae(h1);

        m1.setCurrentTekton(t2);
        m1.addHyphae(h1);

        h1.addMycelium(m1);
        h1.addHyphae(h2);
        h1.setCurrentTekton(t2);

        h2.addHyphae(h1);

        System.out.println("[Tester] growHyphaeToTekton(" + h1 + t1 + ") -> [Mycologist]");
        mycologist.growHyphaeToTekton(h1, t1);
    }
}
