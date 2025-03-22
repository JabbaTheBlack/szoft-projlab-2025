package hu.bme.tests;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.managers.FungalManager;
import hu.bme.managers.SporeManager;
import hu.bme.tekton.MultiTypeTekton;

public class TestUjGombatestNovesztese {
	public static void test_uj_gombatest_novesztese() {
		MultiTypeTekton t1 = new MultiTypeTekton();
		MultiTypeTekton t2 = new MultiTypeTekton();

		Mycelium m1 = new Mycelium();

		Mycologist myc1 = new Mycologist();
		
		Hyphae h1 = new Hyphae();
		Hyphae h2 = new Hyphae();
		Hyphae h3 = new Hyphae();

		DefensiveSpore s1 = new DefensiveSpore();
		DefensiveSpore s2 = new DefensiveSpore();
		DefensiveSpore s3 = new DefensiveSpore();

		FungalManager fm = new FungalManager();

		SporeManager sm = new SporeManager();

		fm.setSporeManager(sm);

		sm.addSpore(s1);
		sm.addSpore(s2);
		sm.addSpore(s3);

		t1.addSpore(s1);
		t1.addSpore(s2);
		t1.addSpore(s3);
		t1.addHyphae(h3);

		myc1.addMycelium(m1);
		
		m1.addHyphae(h1);
		m1.setCurrentTekton(t2);

		t2.addMycelium(m1);
		t2.addHyphae(h1);
		t2.addHyphae(h2);

		h1.addMycelium(m1);

		h1.addHyphae(h2);
		h1.addHyphae(h3);
		h2.addHyphae(h1);
		h2.addHyphae(h3);
		h3.addHyphae(h1);
		h3.addHyphae(h2);

		myc1.growMycelium(h3, t1);

	}
}