package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.SporeEffect;
import hu.bme.tekton.MultiTypeTekton;

public class TestGombaFonalNovesztesMultiTypeTektonon {
        
    public static void test_gombafonal_novesztes_multi_type_tektonon() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        Mycelium m1 = new Mycelium();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();

        t1.addNeighbour(t2);

        t2.addNeighbour(t2);
        t2.addMycelium(m1);

        m1.setCurrentTekton(t2);

        h1.addMycelium(m1);
        h1.addHyphae(h2);

        h2.addHyphae(h1);
        // TODO implement the growHyphae function and deicde what to do with the sporeEffect
    }
    
}
