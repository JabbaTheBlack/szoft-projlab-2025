package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.insect.Insect;
import hu.bme.tekton.MultiTypeTekton;

public class TestGombaFonalElvagasaNincsSporaHatas {
    public static void test_gombafonal_elvagasa_nincs_spora_hatas() {
        // Create the Tektons
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        MultiTypeTekton t3 = new MultiTypeTekton();
        MultiTypeTekton t4 = new MultiTypeTekton();

        // Create the Myceliums
        Mycelium m1 = new Mycelium();
        Mycelium m2 = new Mycelium();

        // Create the Hyphaes
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();
        Hyphae h4 = new Hyphae();
        Hyphae h5 = new Hyphae();
        Hyphae h6 = new Hyphae();
        Hyphae h7 = new Hyphae();

        // Create the insect
        Insect insect = new Insect();

        // Connect the tektons to each other and to the fungi
        t1.addNeighbour(t2);
        t1.connectToTekton(t2);
        t1.addMycelium(m1);
        t1.addHyphae(h1);
        t1.addHyphae(h2);

        t2.addNeighbour(t1);
        t2.addNeighbour(t3);
        t2.connectToTekton(t1);
        t2.connectToTekton(t3);
        t2.addHyphae(h2);
        t2.addHyphae(h3);
        t2.addHyphae(h4);

        t3.addNeighbour(t2);
        t3.addNeighbour(t4);
        t3.connectToTekton(t2);
        t3.connectToTekton(t4);
        t3.addHyphae(h4);
        t3.addHyphae(h5);
        t3.addHyphae(h6);

        t4.addNeighbour(t3);
        t4.connectToTekton(t3);
        t4.addMycelium(m2);
        t4.addHyphae(h6);
        t4.addHyphae(h7);

        // Connect the insect
        insect.setCurrentTekton(t2);

        // Connect the fungi
        m1.setCurrentTekton(t1);
        
        m2.setCurrentTekton(t4);
        m2.addHyphae(h7);

        // Connect the hyphaes
        h1.addMycelium(m1);
        h1.addHyphae(h2);

        h2.addHyphae(h1);
        h2.addHyphae(h3);

        h3.addHyphae(h2);
        h3.addHyphae(h4);

        h4.addHyphae(h3);
        h4.addHyphae(h5);

        h5.addHyphae(h4);
        h5.addHyphae(h6);

        h6.addHyphae(h5);
        h6.addHyphae(h7);

        h7.addHyphae(h6);
        h7.addMycelium(m2);

        System.out.println("[Insect] cutting hyphae: " + h2);
        insect.cutHyphae(t2);
        System.out.println("T1 and T2 tektons are connected:" + t1.isConnectedTo(t2));
    }
}
