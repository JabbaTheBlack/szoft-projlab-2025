package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.CopySpore;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.MultiTypeTekton;
import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;

/**
 * Tests the unsuccessful growth of a new mycelium due to insufficient spores.
 * This test case simulates the interaction between mycelium, hyphae, a mycologist, and tekton structures.
 * 
 * @see Mycelium
 * @see Hyphae
 * @see Mycologist
 * @see DefensiveSpore
 * @see MultiTypeTekton
 */

public class alma {
    public static void test_uj_gombatest_novesztese_sikertelen_nincs_spora() {
        Mycelium m1 = new Mycelium();
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();
        Mycologist mycologist = new Mycologist();
        CopySpore s1 = new CopySpore();
        CopySpore s2  = new CopySpore();
        Entomologist entomologist = new Entomologist();
        Insect insect = new Insect();

        
        
        m1.setCurrentTekton(t2);
        m1.addHyphae(h1);

        t2.addMycelium(m1);
        t2.addHyphae(h1);
        t2.addHyphae(h2);

        h1.addMycelium(m1);
        h1.addHyphae(h2);
        h1.addCurrentTekton(t2);

        h2.addHyphae(h1);
        h2.addHyphae(h3);
        h2.addCurrentTekton(t1);
        h2.addCurrentTekton(t2);

        h3.addHyphae(h2);
        h3.addCurrentTekton(t1);

        t1.addHyphae(h3);
        t1.addSpore(s1);
        t1.addSpore(s2);
        t1.connectToTekton(t2);
        t2.connectToTekton(t1);

        entomologist.addInsect(insect);
        insect.setEntomologist(entomologist);


        insect.eatSpore(s2);

        // sys print vagy mi legyen?
        
    }
}
