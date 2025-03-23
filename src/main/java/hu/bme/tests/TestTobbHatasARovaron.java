package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.insect.Insect;
import hu.bme.tekton.MultiTypeTekton;

public class TestTobbHatasARovaron {
    public static void test_tobb_hatas_a_rovaron() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        StunSpore s1 = new StunSpore();
        DefensiveSpore ds1 = new DefensiveSpore();
        Hyphae h1 = new Hyphae();
        Insect i1 = new Insect();

        t1.addHyphae(h1);
        t2.addHyphae(h1);
        t1.connectToTekton(t2);
        t2.connectToTekton(t1);

        i1.setCurrentTekton(t1);
        t1.addSpore(ds1);
        t1.addSpore(s1);

        System.out.println("[Tester] eatSpore(" + ds1 + ") -> [" + i1 + "]");
        i1.eatSpore(ds1);
        System.out.println("[Tester] cutHyphae(" + h1 + ") -> [" + i1 + "]");
        i1.cutHyphae(h1);        
        i1.move(t2);
        i1.move(t1);

        System.out.println("[Tester] eatSpore(" + s1 + ") -> [" + i1 + "]");
        i1.eatSpore(s1);
    }
}
