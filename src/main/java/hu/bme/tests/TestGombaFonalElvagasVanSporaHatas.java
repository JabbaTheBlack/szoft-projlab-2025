package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.insect.Insect;
import hu.bme.tekton.MultiTypeTekton;


/**
 * Tests the scenario where a fungal network is disrupted by an insect cutting a hyphae,
 * and there is a defensive spore effect applied to the insect, denies the cut.
 * THERE IS SPORE EFFECT
 * 
 * @see Hyphae
 * @see Mycelium
 * @see Insect
 * @see MultiTypeTekton
 * @see DefensiveSpore
 */

public class TestGombaFonalElvagasVanSporaHatas {
    
    public static void test_gombafonal_elvagasa_van_spora_hatas() {

        // Create the Tektons
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();

        // Create the Myceliums
        Mycelium m1 = new Mycelium();

        // Create the Hyphaes
        Hyphae h1 = new Hyphae();
        Hyphae h2 = new Hyphae();
        Hyphae h3 = new Hyphae();

        //Connect everything
        h1.addHyphae(h2);
        h1.addMycelium(m1);

        h2.addHyphae(h1);
        h2.addHyphae(h3);

        h3.addHyphae(h2);

        m1.addHyphae(h1);
        m1.setCurrentTekton(t2);

        t2.addMycelium(m1);
        t2.addNeighbour(t1);
        t2.connectToTekton(t1);

        t1.addNeighbour(t2);
        t1.connectToTekton(t2);
        // Create the insect
        Insect insect = new Insect();
        insect.setCurrentTekton(t1);
        
        DefensiveSpore spore = new DefensiveSpore();
        spore.applyEffect(insect);

        
        System.out.println("[Tester] cutHyphae(" + h2 + ") -> [Insect]");
        insect.cutHyphae(h2);
    }
}
