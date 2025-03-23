package hu.bme.tests;

import java.util.Scanner;

import hu.bme.fungi.Mycelium;
import hu.bme.fungi.Mycologist;
import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.SlowingSpore;
import hu.bme.fungi.spore.SpeedBoostSpore;
import hu.bme.fungi.spore.Spore;
import hu.bme.fungi.spore.StunSpore;
import hu.bme.tekton.MultiTypeTekton;

/**
 * Tests the selection of different fungal spore types, like the start of the game.
 * This test case simulates user interaction to choose a type of spore for mycelium.
 * 
 * @see Mycelium
 * @see Mycologist
 * @see Spore
 */

public class TestGombaTipusValasztas {
    public static void test_gomba_tipus_valasztas_sikeres(Scanner scanner){
        MultiTypeTekton t1 = new MultiTypeTekton();
        Mycologist mycologist = new Mycologist();

        System.out.println("Choose a spore type: ");
        System.out.println("1. StunSpore");
        System.out.println("2. DefensiveSpore");
        System.out.println("3. SpeedBoostSpore");
        System.out.println("4. SlowingSpore");

        int choice = scanner.nextInt();
        System.out.println("[Tester] chooseSpore("+choice+") -> [Mycologist]");
        mycologist.chooseSpore(choice);

    }
}
