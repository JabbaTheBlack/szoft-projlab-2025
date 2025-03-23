package hu.bme.tests;

import java.util.Scanner;

import hu.bme.fungi.Hyphae;
import hu.bme.fungi.Mycelium;
import hu.bme.insect.Insect;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the scenario where an insect moves to another connected tekton, through a hyphae.
 * 
 * This class simulates a network of tektons, myceliums, and hyphaes, and tests the behavior
 * when an insect moves from one tekton to another.
 * 
 * @see Insect
 * @see Mycelium
 * @see Hyphae
 * @see MultiTypeTekton
 */

public class TestRovarAtlepMasikTektonra {
    
    public static void test_rovar_atlep_masik_tektonra(Scanner scanner) {
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
        t1.addMycelium(m1);

        t2.addNeighbour(t1);

        t3.addNeighbour(t1);

        h1.addMycelium(m1);
        h1.addHyphae(h2);

        h2.addHyphae(h1);

        m1.addHyphae(h1);

        System.out.println("Are the 2 Tektons connected? (yes/no)");
        String input = scanner.nextLine().trim().toLowerCase();
        

        if(input.equals("yes")) {
            t1.connectToTekton(t2);
            t2.connectToTekton(t1);            
        }

        System.out.println("[Tester] move(" + t1 + ") -> [Insect]");
        i1.move(t1);
    }
}
