package hu.bme.tests;

import hu.bme.tekton.MultiTypeTekton;

public class TestTektonToresSzomszedokFrissitese {
    public static void test_tekton_tores_szomszedok_frissitese() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        MultiTypeTekton t2 = new MultiTypeTekton();
        MultiTypeTekton t3 = new MultiTypeTekton();

        t1.addNeighbour(t2);
        t2.addNeighbour(t1);
        t2.addNeighbour(t3);
        t3.addNeighbour(t2);

        t2.breakApart();
    }
}
