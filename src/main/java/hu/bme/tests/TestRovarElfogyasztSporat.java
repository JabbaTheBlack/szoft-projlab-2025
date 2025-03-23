package hu.bme.tests;

import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.insect.Insect;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the scenario where an insect consumes a spore and experiences its effects.
 * At this point, it is a defensive spore. It should work the same way as the other spores, other than the effect.
 * 
 * This class simulates an insect eating a defensive spore and tests the behavior or effects
 * resulting from this action.
 * 
 * @see Insect
 * @see DefensiveSpore
 */

public class TestRovarElfogyasztSporat {
    public static void test_rovar_elfogyaszt_sporat_es_megkapja_hatasat() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        Insect i1 = new Insect();
        DefensiveSpore s1 = new DefensiveSpore();
        i1.setCurrentTekton(t1);
        t1.addSpore(s1);

        System.out.println("[Tester] eatSpore(" + s1 + ") -> [Insect]");
        i1.eatSpore(s1);
    }
}
