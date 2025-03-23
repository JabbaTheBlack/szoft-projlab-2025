package hu.bme.tests;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.tekton.MultiTypeTekton;

public class TestGombatestFejleszteseSporaszintNemEleg {
    public static void test_gombatest_nem_fejlesztheto_nincs_sporaszint() {
        MultiTypeTekton t1 = new MultiTypeTekton();
        Mycelium m1 = new Mycelium<>();
        DefensiveSpore s1 = new DefensiveSpore();
        DefensiveSpore s2 = new DefensiveSpore();

        t1.addMycelium(m1);
        m1.setCurrentTekton(t1);
        t1.addSpore(s1);
        t1.addSpore(s2);

        System.out.println("[Tester] upgrade() -> [" + m1 + "]");
        m1.upgrade();
    }
}
