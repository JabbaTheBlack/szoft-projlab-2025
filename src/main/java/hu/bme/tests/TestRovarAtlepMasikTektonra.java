package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.insect.Insect;
import hu.bme.tekton.MultiTypeTekton;

public class TestRovarAtlepMasikTektonra {
    
    public static void test_rovar_atlep_masik_tektonra() {
        Insect i1 = new Insect();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        MultiTypeTekton t3 = new MultiTypeTekton();
        Mycelium m1 = new Mycelium();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();

        i1.setCurrentTekton(t2);
        
        t1.addNeighbour(t3);
        t1.addNeighbour(t2);
        t1.connectToTekton(t2);
        t1.addMycelium(m1);

        t2.addNeighbour(t1);
        t2.connectToTekton(t1);

        t3.addNeighbour(t1);

        h1.addMycelium(m1);
        h1.addHyphae(h2);

        h2.addHyphae(h1);

        m1.addHyphae(h1);

        System.out.println("Rovar jelenlegi tektonja: " + i1.getCurrentTekton());
        i1.move(t1);
        System.out.println("Rovar új tektonja: " + i1.getCurrentTekton());
    }
}
