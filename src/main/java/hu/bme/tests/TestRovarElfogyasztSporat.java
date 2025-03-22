package hu.bme.tests;

import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.insect.Insect;

public class TestRovarElfogyasztSporat {
    public static void test_rovar_elfogyaszt_sporat_es_megkapja_hatasat() {
        Insect i1 = new Insect();
        DefensiveSpore s1 = new DefensiveSpore();

        System.out.println("[Tester] eatSpore(" + s1 + ") -> [Insect]");
        i1.eatSpore(s1);
    }
}
