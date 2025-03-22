package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.tekton.SingleTypeTekton;

public class TestGombaFonalNoveszteseSingleTypeTektonon {
    
    public static void test_gombafonal_novesztese_single_type_tektonon() {
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();

        // Create mycologists
        Mycologist myc1 = new Mycologist();
        Mycologist myc2 = new Mycologist();

        // Create myceliums
        Mycelium m1 = new Mycelium();
        Mycelium m2 = new Mycelium();

        // Create tektons
        SingleTypeTekton t1 = new SingleTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();

        h1.setOwner(myc1);
        h1.setCurrentTekton(t1);
        t1.addMycelium(m1);
        m1.addHyphae(h1);
        t1.addHyphae(h1);

        t2.addMycelium(m2);
        h2.setOwner(myc2);
        h2.setCurrentTekton(t2);
        m2.addHyphae(h2);
        t2.addHyphae(h2);


        System.out.println("[Tester] growHyphaeToTekton(" + h2 + ", " + t1 + ") -> [Mycologist]");
        myc2.growHyphaeToTekton(h2, t1);
    }
}
