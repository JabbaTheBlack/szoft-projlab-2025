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

           Mycelium<? extends Spore> mycelium = null;  // Declare a common variable for mycelium

        switch (choice) {
            case 1:
                mycelium = new Mycelium<StunSpore>();    
                break;
            case 2:
                mycelium = new Mycelium<DefensiveSpore>();
                break;
            case 3:
                mycelium = new Mycelium<SpeedBoostSpore>();
                break;
            case 4:
                mycelium = new Mycelium<SlowingSpore>();
                break;
            default:
                System.out.println("Invalid choice.");
                return;  
        }
    }
}
