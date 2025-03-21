package hu.bme;

import java.util.Scanner;

import hu.bme.tests.TestGombaFonalElvagasVanSporaHatas;
import hu.bme.tests.TestGombaFonalElvagasaNincsSporaHatas;
import hu.bme.tests.TestRovarAtlepMasikTektonra;

public class TestRunner {
    public void runTests() {
        Scanner scanner = new Scanner(System.in);  
        int choice = 0;
        do {
            printTestOptions();
            System.out.print("Adja meg a teszteset számát: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 :
                    TestGombaFonalElvagasaNincsSporaHatas.test_gombafonal_elvagasa_nincs_spora_hatas();
                    waitForEnter(scanner);
                    break;
                case 2 : 
                    TestGombaFonalElvagasVanSporaHatas.test_gombafonal_elvagasa_van_spora_hatas();
                    waitForEnter(scanner);
                    break; 
                case 3 :
                    TestRovarAtlepMasikTektonra.test_rovar_atlep_masik_tektonra(scanner);
                    waitForEnter(scanner);
                    break;
                // case 4 -> 
                // case 5 -> 
                // case 6 -> 
                // case 7 -> 
                // case 8 -> 
                // case 9 -> 
                // case 10 -> 
                // case 11 -> 
                // case 12 -> 
                // case 13 -> 
                // case 14 -> 
                // case 15 -> 
                // case 16 -> 
                // case 17 -> 
                // case 18 -> 
                // case 19 -> 
                // case 20 -> 
                // case 21 -> 
                // case 22 -> 
                // case 23 -> 
                case 24 : 
                    System.out.println("Kilépés...");
                    break;
                default:
                    System.out.println("Érvénytelen választás.");
                    break;
            }

        }while(choice != 24);
        
        scanner.close();
    }

    private void waitForEnter(Scanner scanner) {
        System.out.println("\nNyomjon entert a folytatáshoz...");
        scanner.nextLine();
    }

    private void printTestOptions() {
        System.out.println("Válasszon egy tesztesetet az alábbi listából:");
        System.out.println("1 - test_gombafonal_elvagasa_nincs_spora_hatas");
        System.out.println("2 - test_gombafonal_elvagasa_rovar_spora_hatas");
        System.out.println("3 - test_rovar_atlep_masik_tektonra");
        System.out.println("4 - test_gombafonal_novesztese_multitype_tektonon");
        System.out.println("5 - test_gombafonal_novesztese_single_type_tektonon");
        System.out.println("6 - test_rovar_elfogyaszt_sporat_es_megkapja_hatasat");
        System.out.println("7 - test_gombafonal_novesztese_nem_szomszedos_tektonok_kozott");
        System.out.println("8 - test_gombafonal_felszivodasa_felszivo_tektonon");
        System.out.println("9 - test_gombafonal_elszakadasa_tekton_toresnel");
        System.out.println("10 - test_tekton_tores_szomszedok_frissitese");
        System.out.println("11 - test_uj_gombatest_novesztese_sikeres");
        System.out.println("12 - test_uj_gombatest_novesztese_sikertelen_nincs_spora");
        System.out.println("13 - test_uj_gombatest_novesztese_nincs_fonal");
        System.out.println("14 - test_uj_gombatest_nem_no_ha_mar_van");
        System.out.println("15 - test_spora_kilovese_szomszedos_tektonra");
        System.out.println("16 - test_spora_kilovese_szomszeddal_szomszedos_tektonra");
        System.out.println("17 - test_spora_gyorsitja_fonal_novekedest");
        System.out.println("18 - test_tekton_nem_torhet_gombatest_miatt");
        System.out.println("19 - test_gomba_tipus_valasztas_sikeres");
        System.out.println("20 - test_gombatest_fejlesztese_megfelelo_sporaszinttel");
        System.out.println("21 - test_gombatest_nem_fejlesztheto_nincs_sporaszint");
        System.out.println("22 - test_gombatest_elhal_spora_szetszoras_utan");
        System.out.println("23 - test_tobb_hatas_a_rovaron");
        System.out.println("24 - Kilépés");
    }
}
