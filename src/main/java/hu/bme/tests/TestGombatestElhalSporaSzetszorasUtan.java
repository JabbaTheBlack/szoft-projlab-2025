package hu.bme.tests;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.managers.MyceliumManager;
import hu.bme.managers.SporeManager;
import hu.bme.tekton.MultiTypeTekton;

public class TestGombatestElhalSporaSzetszorasUtan {
    public static void TestGombaFonalNovesztesMultiTypeTektonon(){
        Mycelium m1 = new Mycelium();
        MultiTypeTekton t1 = new MultiTypeTekton();
        DefensiveSpore s1 = new DefensiveSpore();
        MyceliumManager MM = new MyceliumManager();
        m1.setCurrentTekton(t1);
        t1.addMycelium(m1);
        m1.addSpore(s1);
        MM.addMycelium(m1);
        m1.maxSporeRelease = 1;
        m1.releaseSpores();
        if(m1.maxSporeRelease==0){
            MM.removeMycelium(m1);
        }
        

        

    }
}
